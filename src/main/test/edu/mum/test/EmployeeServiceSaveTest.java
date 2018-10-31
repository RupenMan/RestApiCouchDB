package edu.mum.test;

import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import edu.mum.Employee;
import edu.mum.EmployeeDao;
import edu.mum.EmployeeService;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeServiceSaveTest {
	
	Employee emp = new Employee();
	
	public EmployeeServiceSaveTest() {
		// TODO Auto-generated constructor stub
		emp.setName("Rupendra");
		emp.setDept("GRT");
		emp.setSalary(10000);
		
	}
	
	@Mock
	private EmployeeDao employeeDao;
	
	@InjectMocks
	private EmployeeService empService;
	
	@Test
	public void saveValidEmployee() {
		when(employeeDao.save(emp)).thenReturn(emp);
		Assert.assertEquals(200, empService.save(emp).getStatus());
	}
	
	@Test
	public void saveNoNameEmployee() {
		emp.setName("");
		when(employeeDao.save(emp)).thenReturn(emp);
		Assert.assertEquals(204, empService.save(emp).getStatus());
	}
	
	@Test
	public void saveNoDeptEmployee() {
		emp.setDept(null);
		when(employeeDao.save(emp)).thenReturn(emp);
		Assert.assertEquals(204, empService.save(emp).getStatus());
	}
	
	@Test
	public void saveNegateSalaryOfEmployee() {
		emp.setSalary(-15000);
		when(employeeDao.save(emp)).thenReturn(emp);
		Assert.assertEquals(406, empService.save(emp).getStatus());
	}
}
