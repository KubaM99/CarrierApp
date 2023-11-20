package com.example.App.exceptation;

public class CustomerExsistExceptation  extends RuntimeException{
    
    private static final long serialVersionUID = 1L;

    public CustomerExsistExceptation(String message) {
	super(message);
	
    }
    
    

}
