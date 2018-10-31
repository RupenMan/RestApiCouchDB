package edu.mum;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("employee")
public class EmployeeService {

	EmployeeDao employeeDao = new EmployeeDao();

	@GET
	@Path("/findAll")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findAll() {
		List<Employee> empList = employeeDao.findAll();
		return empList != null ? Response.ok(empList, MediaType.APPLICATION_JSON).build()
				: Response.status(Response.Status.NO_CONTENT).build();
	}

	@POST
	@Path("/save")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response save(Employee emp) {
		if (emp.getName() == null || emp.getName().equals("")) {
			return Response.status(Response.Status.NOT_ACCEPTABLE).entity("NAME NOT FOUND ERROR")
					.type(MediaType.TEXT_HTML).build();
		} else if (emp.getDept() == null || emp.getDept().equals("")) {
			return Response.status(Response.Status.NOT_ACCEPTABLE).entity("DEPARTMENT NOT AVAILABLE ERROR")
					.type(MediaType.TEXT_HTML).build();
		} else if (emp.getSalary() <= 0) {
			return Response.status(Response.Status.NOT_ACCEPTABLE).entity("Invalid Salary").build();
		}
		employeeDao.save(emp);
		return Response.ok(emp, MediaType.APPLICATION_JSON).build();
	}

	@PUT
	@Path("/update")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response update(Employee emp) {
		boolean updateStatus = employeeDao.update(emp);
		return updateStatus ? Response.status(Response.Status.OK).build()
				: Response.status(Response.Status.NOT_MODIFIED).build();
	}

	@DELETE
	@Path("/delete/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response delete(@PathParam("id") String id) {
		boolean delStatus = employeeDao.delete(id);
		return delStatus ? Response.status(Response.Status.OK).build()
				: Response.status(Response.Status.NO_CONTENT).build();
	}
}
