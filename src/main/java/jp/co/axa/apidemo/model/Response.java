package jp.co.axa.apidemo.model;

import lombok.Data;
import jp.co.axa.apidemo.common.ResponseStatus;

@Data
public class Response<T> {
	
	private ResponseStatus status;
	
	private String message;
	
	private T data;
	
}
