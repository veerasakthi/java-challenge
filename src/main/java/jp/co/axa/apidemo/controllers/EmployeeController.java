package jp.co.axa.apidemo.controllers;

import jp.co.axa.apidemo.common.ResponseUtils;
import jp.co.axa.apidemo.common.UriConstants;
import jp.co.axa.apidemo.entities.Employee;
import jp.co.axa.apidemo.model.Response;
import jp.co.axa.apidemo.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import javax.validation.Valid;

@RestController
@RequestMapping(UriConstants.API_V1)
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	/**
	 * 
	 * 
	 * @return
	 */
	@GetMapping(UriConstants.GET_ALL_EMPLOYEES)
	public ResponseEntity<Response<List<Employee>>> getEmployees() {

		// get employee list from db
		List<Employee> employees = employeeService.retrieveEmployees();

		return ResponseUtils.success(employees);
	}

	/**
	 * Used to get Employee by id
	 * 
	 * @param employeeId
	 * @return employee
	 */
	@GetMapping(UriConstants.GET_EMPLOYEE_BY_ID)
	public ResponseEntity<Response<Employee>> getEmployee(@PathVariable(name = "employeeId") Long employeeId) {

		// get employee details
		Employee employee = employeeService.getEmployee(employeeId);

		return ResponseUtils.success(employee);
	}

	@PostMapping(UriConstants.SAVE_EMPLOYEE)
	public ResponseEntity<Response<Boolean>> saveEmployee(@Valid @RequestBody Employee employee) {

		// save employee to db
		employeeService.saveEmployee(employee);
		System.out.println("Employee Saved Successfully");

		return ResponseUtils.success(true);
	}

	@DeleteMapping(UriConstants.DELETE_EMPLOYEE_BY_ID)
	public ResponseEntity<Response<Boolean>> deleteEmployee(@PathVariable(name = "employeeId") Long employeeId) {

		// delete emp from db
		employeeService.deleteEmployee(employeeId);
		System.out.println("Employee Deleted Successfully");

		return ResponseUtils.success(true);
	}
	
	@PutMapping(UriConstants.UPDATE_EMPLOYEE_BY_ID)
	public ResponseEntity<Response<String>> updateEmployee(@RequestBody Employee employee) {
		// check if employee exist or not
		Employee existingEmp = employeeService.getEmployee(employee.getId());

		if (existingEmp == null) {
			// return employee not exist error message
			return ResponseUtils.error("Employee is not found for given employeeId: "+ employee.getId(), HttpStatus.BAD_REQUEST);
		}

		// update the employee
		employeeService.updateEmployee(employee);
		return ResponseUtils.success("Employee updated successfully");
	}

}
