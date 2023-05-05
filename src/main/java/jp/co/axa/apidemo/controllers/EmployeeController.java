package jp.co.axa.apidemo.controllers;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import jp.co.axa.apidemo.common.ResponseUtils;
import jp.co.axa.apidemo.common.UriConstants;
import jp.co.axa.apidemo.entities.Employee;
import jp.co.axa.apidemo.model.Response;
import jp.co.axa.apidemo.services.EmployeeService;

@RestController
@RequestMapping(UriConstants.API_V1)
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;
	
	private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

	/**
	 * Get the list of all employees
	 * 
	 * @return Response<List<Employee>>
	 */
	@Operation(summary = "list of all employees", description = "Get the list of all employees")
	@GetMapping(UriConstants.GET_ALL_EMPLOYEES)
	public ResponseEntity<Response<List<Employee>>> getEmployees() {

		// get employee list from db
		List<Employee> employees = employeeService.retrieveEmployees();
		logger.info("employee list executed");

		return ResponseUtils.success(employees);
	}

	/**
	 * get Employee details by id
	 * 
	 * @param employeeId
	 * @return Response<Employee>
	 */
	@Operation(summary = "get Employee by id", description = "get Employee details by id")
	@GetMapping(UriConstants.GET_EMPLOYEE_BY_ID)
	public ResponseEntity<Response<Employee>> getEmployee(@PathVariable(name = "employeeId") Long employeeId) {

		// get employee details
		Employee employee = employeeService.getEmployee(employeeId);
		logger.info("get employee details executed");

		return ResponseUtils.success(employee);
	}

	/**
	 * save employee details
	 * 
	 * @param Employee
	 * @return Response<Boolean>
	 */
	@Operation(summary = "save employee", description = "save employee details")
	@PostMapping(UriConstants.SAVE_EMPLOYEE)
	public ResponseEntity<Response<Boolean>> saveEmployee(@Valid @RequestBody Employee employee) {

		// save employee to db
		employeeService.saveEmployee(employee);
		logger.info("Employee Saved Successfully");

		return ResponseUtils.success(true);
	}

	/**
	 * delete employee by id
	 * 
	 * @param employeeId
	 * @return Response<Boolean>
	 */
	@Operation(summary = "delete employee by id", description = "delete employee details by id")
	@DeleteMapping(UriConstants.DELETE_EMPLOYEE_BY_ID)
	public ResponseEntity<Response<Boolean>> deleteEmployee(@PathVariable(name = "employeeId") Long employeeId) {

		// delete emp from db
		employeeService.deleteEmployee(employeeId);
		logger.info("Employee Deleted Successfully");

		return ResponseUtils.success(true);
	}
	
	/**
	 * update employee
	 * 
	 * @param Employee
	 * @return Response<Boolean>
	 */
	@Operation(summary = "update employee", description = "update employee details by id")
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
		logger.info("Employee updated successfully");
		return ResponseUtils.success("Employee updated successfully");
	}

}
