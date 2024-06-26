package com.appsdeveloperblog.app.ws.exceptions;

import java.net.http.HttpHeaders;
import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.appsdeveloperblog.app.ws.ui.model.response.ErrorMessage;

@ControllerAdvice
public class AppExceptionsHandler extends ResponseEntityExceptionHandler {
	@ExceptionHandler(value = {Exception.class})
	public ResponseEntity<Object> handleAnyException(Exception ex,WebRequest request){
		
		String errorMessageDesc=ex.getLocalizedMessage();
		
		if(errorMessageDesc==null) {
			errorMessageDesc=ex.toString();
		}
		ErrorMessage errorMessage=new ErrorMessage(new Date(),ex.getLocalizedMessage());
		
		return new ResponseEntity<Object>(errorMessage,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(value = {UserServiceException.class})
	public ResponseEntity<Object> handleUserServiceException(UserServiceException ex,WebRequest request){
		
		String errorMessageDesc=ex.getLocalizedMessage();
		
		if(errorMessageDesc==null) {
			errorMessageDesc=ex.toString();
		}
		ErrorMessage errorMessage=new ErrorMessage(new Date(),ex.getLocalizedMessage());
		
		return new ResponseEntity<Object>(errorMessage,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
