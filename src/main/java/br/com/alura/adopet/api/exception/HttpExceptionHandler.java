package br.com.alura.adopet.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class HttpExceptionHandler {
	
	@ExceptionHandler(ValidacaoException.class)
	public ResponseEntity<String> handleValidacaoException(ValidacaoException ex) {
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
	}
	
//	@ExceptionHandler(Exception.class)
//	public ResponseEntity<String> handleGlobalException(Exception ex) {
//		return new ResponseEntity<>("An unexpected error occurred: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
//	}
}