package edu.mum.test;

import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import edu.mum.EmployeeDao;
import edu.mum.EmployeeService;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeServiceDeleteTest {
	
	@Mock
	public EmployeeDao empDao;
	
	@InjectMocks
	public EmployeeService empService;
	
	@Test
	public void deleteValidEmployee() {
		when(empDao.delete("1")).thenReturn(true);
		Assert.assertEquals(200, empService.delete("1").getStatus());
	}
	
	@Test 
	public void deleteInValidEmployee() {
		when(empDao.delete("2")).thenReturn(false);
		Assert.assertEquals(204, empService.delete("2").getStatus());
	}
}
