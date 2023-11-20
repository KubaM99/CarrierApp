package com.example.App.exceptation;



import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptation {
    
    @ExceptionHandler(UserNotFoundExceptaion.class)
    public ResponseEntity<ExceptatopnObject> handlerUserNotFoundExceptaion(UserNotFoundExceptaion ex, WebRequest request){
	
	ExceptatopnObject exceptatopnObject = new ExceptatopnObject();
	
	exceptatopnObject.setCode(HttpStatus.NOT_FOUND.value());
	exceptatopnObject.setMassage(ex.getMessage());
	exceptatopnObject.setTime(new Date());
	
	
	
	return new ResponseEntity<ExceptatopnObject>(exceptatopnObject, HttpStatus.NOT_FOUND);
	
    }
    
    @ExceptionHandler(CustomerExsistExceptation.class)
    public ResponseEntity<ExceptatopnObject> handlerUserFoundExceptaion(CustomerExsistExceptation ex, WebRequest request){
	
	ExceptatopnObject exceptatopnObject = new ExceptatopnObject();
	
	exceptatopnObject.setCode(HttpStatus.OK.value());
	exceptatopnObject.setMassage(ex.getMessage());
	exceptatopnObject.setTime(new Date());
	
	
	
	return new ResponseEntity<ExceptatopnObject>(exceptatopnObject, HttpStatus.OK);
	
    }
    


}
