package jp.co.axa.apidemo.common;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import jp.co.axa.apidemo.model.Response;

public class ResponseUtils {

	private ResponseUtils() {

	}

	public static <T> ResponseEntity<Response<T>> success(T data) {
		Response<T> body = new Response<>();
		body.setData(data);
		body.setStatus(ResponseStatus.success);
		return ResponseEntity.ok(body);
	}
	
	public static <T> ResponseEntity<Response<T>> error(String message, HttpStatus statusCode) {
		Response<T> body = new Response<>();
		body.setMessage(message);
		body.setStatus(ResponseStatus.error);
		return ResponseEntity.status(statusCode).body(body);
	}
}
