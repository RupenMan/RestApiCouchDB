package edu.mum;

import java.util.List;

import org.ektorp.CouchDbConnector;
import org.ektorp.CouchDbInstance;
import org.ektorp.ViewQuery;

public class EmployeeDao {
	
	private CouchDbInstance dbInstance = DBConnector.getInstance();
	CouchDbConnector db = null;
	
	public EmployeeDao() {
		db = dbInstance.createConnector("employee", true);
		db.createDatabaseIfNotExists();
	}
	
	public List<Employee> findAll(){
		ViewQuery view = new ViewQuery().allDocs()
				.includeDocs(true);
		return db.queryView(view,Employee.class);
	}

	public Employee save(Employee emp) {
		db.create(emp);
		return emp;
	}

	public Employee update(Employee emp) {
		Employee employee = db.get(Employee.class, emp.getId());
		employee.setName(emp.getName());
		employee.setSalary(emp.getSalary());
		employee.setDept(emp.getDept());
		db.update(employee);
		return emp;
	}

	public void delete(String id) {
		Employee employee = findById(id);
		db.delete(employee);
	}
	
	public Employee findById(String id) {
		return db.get(Employee.class, id);
	}
	
}
