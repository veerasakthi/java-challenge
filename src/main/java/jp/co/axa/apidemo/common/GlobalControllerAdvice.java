package jp.co.axa.apidemo.common;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jp.co.axa.apidemo.model.Response;

@RestControllerAdvice
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class GlobalControllerAdvice // extends ResponseEntityExceptionHandler
{
	private static final Logger logger = LoggerFactory.getLogger(GlobalControllerAdvice.class);

	@ExceptionHandler(Throwable.class)
	@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
	public ResponseEntity<Response<String>> handleCommonException(final Throwable e) {
		String logRef = UUID.randomUUID().toString();
		String message = "Problem occured. logRef=" + logRef;
		logger.error(message, e);
		return ResponseUtils.error(message, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	// Specific Exception can be handled here

}