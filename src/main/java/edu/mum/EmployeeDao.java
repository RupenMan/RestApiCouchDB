package edu.mum;

import java.util.List;

import org.ektorp.CouchDbConnector;
import org.ektorp.CouchDbInstance;
import org.ektorp.DocumentNotFoundException;
import org.ektorp.ViewQuery;

public class EmployeeDao {

	private CouchDbInstance dbInstance = DBConnector.getInstance();
	CouchDbConnector db = null;

	public EmployeeDao() {
		db = dbInstance.createConnector("employee", true);
		db.createDatabaseIfNotExists();
	}

	public List<Employee> findAll() {
		ViewQuery view = new ViewQuery().allDocs().includeDocs(true);
		try {			
			return db.queryView(view, Employee.class);
		}catch(DocumentNotFoundException ex) {
			
		}
		return null;
	}

	public Employee save(Employee emp) {
		db.create(emp);
		return emp;
	}

	public boolean update(Employee emp) {
		Employee employee = findById(emp.getId());
		if (emp.equals(employee))
			return false;
		employee.setName(emp.getName());
		employee.setSalary(emp.getSalary());
		employee.setDept(emp.getDept());
		db.update(employee);
		return true;
	}

	public boolean delete(String id) {
		Employee employee = findById(id);
		if(employee==null) return false;
		db.delete(employee);
		return true;
	}

	public Employee findById(String id) {
		Employee emp = null;
		try {			
			emp = db.get(Employee.class, id);
		}catch(DocumentNotFoundException ex) {
			
		}
		return emp;
	}

}
