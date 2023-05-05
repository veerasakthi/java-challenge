package jp.co.axa.apidemo.controllers;

import jp.co.axa.apidemo.common.UriConstants;
import jp.co.axa.apidemo.entities.Employee;
import jp.co.axa.apidemo.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import javax.validation.Valid;

@RestController
@RequestMapping(UriConstants.API_V1)
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@GetMapping(UriConstants.GET_ALL_EMPLOYEES)
	public List<Employee> getEmployees() {
		List<Employee> employees = employeeService.retrieveEmployees();
		return employees;
	}

	@GetMapping(UriConstants.GET_EMPLOYEE_BY_ID)
	public Employee getEmployee(@PathVariable(name = "employeeId") Long employeeId) {
		return employeeService.getEmployee(employeeId);
	}

	@PostMapping(UriConstants.SAVE_EMPLOYEE)
	public void saveEmployee(@Valid @RequestBody Employee employee) {

		employeeService.saveEmployee(employee);
		System.out.println("Employee Saved Successfully");
	}

	@DeleteMapping(UriConstants.DELETE_EMPLOYEE_BY_ID)
	public void deleteEmployee(@PathVariable(name = "employeeId") Long employeeId) {
		employeeService.deleteEmployee(employeeId);
		System.out.println("Employee Deleted Successfully");
	}

	@PutMapping(UriConstants.UPDATE_EMPLOYEE_BY_ID)
	public void updateEmployee(@RequestBody Employee employee, @PathVariable(name = "employeeId") Long employeeId) {
		// check if employee exist or not
		Employee existingEmp = employeeService.getEmployee(employeeId);

		if (existingEmp != null) {
			// update the employee
			employeeService.updateEmployee(employee);
		}

		// return employee not exist error message

	}

}
