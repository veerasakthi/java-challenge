package jp.co.axa.apidemo.common;

public final class UriConstants {
	private UriConstants() {
		// No need to instantiate the class
	}
	

	public static final String API_V1 = "/api/v1";
	
	public static final String GET_ALL_EMPLOYEES = "/employees";
	public static final String GET_EMPLOYEE_BY_ID = "/employee/{employeeId}";
	public static final String SAVE_EMPLOYEE = "/employee";
	public static final String DELETE_EMPLOYEE_BY_ID = "/employee/{employeeId}";
	public static final String UPDATE_EMPLOYEE_BY_ID = "/employee";
}
