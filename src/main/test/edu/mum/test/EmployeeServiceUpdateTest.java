package edu.mum.test;

import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import edu.mum.Employee;
import edu.mum.EmployeeDao;
import edu.mum.EmployeeService;

import org.junit.Assert;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeServiceUpdateTest{

	Employee emp = new Employee();

	public EmployeeServiceUpdateTest() {
		emp.setName("Rupendra");
		emp.setDept("GRT");
		emp.setSalary(10000);
	}

	@Mock
	public EmployeeDao employeeDao;

	@InjectMocks
	public EmployeeService empService;

	@Test
	public void updateSameEmployeeTest() {
		when(employeeDao.update(emp)).thenReturn(false);
		Assert.assertEquals(304, empService.update(emp).getStatus());
	}
	
	@Test
	public void updateValidEmployeeTest(){
		Employee employee = new Employee();
		employee.setName("Praveen");
		employee.setDept("SBI");
		employee.setSalary(7000);
		when(employeeDao.update(employee)).thenReturn(true);
		Assert.assertEquals(200, empService.update(employee).getStatus());
	}
}
