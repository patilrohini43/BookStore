package com.bridgelabz.bookstore.exception;

import com.bridgelabz.bookstore.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
@RestController
public class GlobalExceptionHandler {
	
    @Autowired
	Environment environment;
	
	@ExceptionHandler(BookException.class)
	  public final ResponseEntity<Response> bookException(BookException e) {

		Response status=new Response(400,e.getMessage());
	    return new ResponseEntity<Response>(status,HttpStatus.OK);
	  }

}
