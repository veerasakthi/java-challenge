package jp.co.axa.apidemo.controllers;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import jp.co.axa.apidemo.common.ResponseStatus;
import jp.co.axa.apidemo.common.UriConstants;
import jp.co.axa.apidemo.entities.Employee;
import jp.co.axa.apidemo.services.EmployeeService;

@SpringBootTest
@AutoConfigureMockMvc
class EmployeeControllerTest {
	
	@Autowired
    private MockMvc mockMvc;
	
	@MockBean
	private EmployeeService employeeService;
	
	@Test
	void testGetEmployees() throws Exception {
		
		ArrayList<Employee> employeeList = new ArrayList<>();
		employeeList.add(new Employee(1L, "veera", 500, "cse"));
		employeeList.add(new Employee(2L, "veera2", 600, "cse2"));
		employeeList.add(new Employee(3L, "veera3", 700, "cse3"));
		when(employeeService.retrieveEmployees()).thenReturn(employeeList);
		
		
		mockMvc.perform(get(UriConstants.API_V1 + UriConstants.GET_ALL_EMPLOYEES))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
        .andExpect(jsonPath("$.status", is(ResponseStatus.success.toString())))
        .andExpect(jsonPath("$.data.size()", is(3)));
	}

	@Test
	void testGetEmployee() throws Exception {
		Long id = 1L;
		Employee employee = new Employee(1L, "veera", 500, "cse");
		when(employeeService.getEmployee(id)).thenReturn(employee);
		

	    mockMvc.perform(get(UriConstants.API_V1 + UriConstants.GET_EMPLOYEE_BY_ID, id))
	            .andExpect(status().isOk())
	            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
	            .andExpect(jsonPath("$.data.id", is(1)))
	            .andExpect(jsonPath("$.data.name", is(employee.getName())))
	            .andExpect(jsonPath("$.data.department", is(employee.getDepartment())))
	            .andExpect(jsonPath("$.data.salary", is(employee.getSalary())));
	}

	@Test
	void testSaveEmployee() throws Exception {
		String requestBody = "{\"name\": \"veera\", \"salary\": 500, \"department\":\"it2\"}";

	    mockMvc.perform(post(UriConstants.API_V1 + UriConstants.SAVE_EMPLOYEE)
	            .contentType(MediaType.APPLICATION_JSON)
	            .content(requestBody))
	            .andExpect(status().isOk())
	            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
	            .andExpect(jsonPath("$.data", is(true)));
	}
	
	@Test
	void testUpdateEmployee() throws Exception {
		String requestBody = "{\"id\": \"1\",\"name\": \"veera2\", \"salary\": 900, \"department\":\"it7\"}";
		
	    mockMvc.perform(put(UriConstants.API_V1 + UriConstants.UPDATE_EMPLOYEE_BY_ID)
	            .contentType(MediaType.APPLICATION_JSON)
	            .content(requestBody))
	            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
	            .andExpect(jsonPath("$.status", is(ResponseStatus.error.toString())));
	}

}
