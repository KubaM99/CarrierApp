package com.example.App.dto;

import java.util.Date;

import lombok.Data;

public class AppMessage {
    
    private String massage1;
    private String massage2;
    private Date time;
    
    public AppMessage(String massage1) {
  	super();
  	this.massage1 = massage1;
  	
      }
    
    public AppMessage(String massage1, Date time) {
	super();
	this.massage1 = massage1;
	this.time = time;
    }

    public AppMessage(String massage1, String massage2, Date time) {
	super();
	this.massage1 = massage1;
	this.massage2 = massage2;
	this.time = time;
    }

    public String getMassage1() {
        return massage1;
    }

    public void setMassage1(String massage1) {
        this.massage1 = massage1;
    }

    public String getMassage2() {
        return massage2;
    }

    public void setMassage2(String massage2) {
        this.massage2 = massage2;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
    
  
    
}
