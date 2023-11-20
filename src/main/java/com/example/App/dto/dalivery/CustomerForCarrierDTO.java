package com.example.App.dto.dalivery;

import jakarta.persistence.Column;

public class CustomerForCarrierDTO {

   
	private String firstName;
	private String lastName;
	private String phone;	
	private String address;
	private String city;
	private String zipCode;
	private String email;
	
	
	
	
	
	public CustomerForCarrierDTO(String firstName, String lastName, String phone, String address, String city,
		String zipCode, String email) {
	    super();
	    this.firstName = firstName;
	    this.lastName = lastName;
	    this.phone = phone;
	    this.address = address;
	    this.city = city;
	    this.zipCode = zipCode;
	    this.email = email;
	}
	public String getFirstName() {
	    return firstName;
	}
	public void setFirstName(String firstName) {
	    this.firstName = firstName;
	}
	public String getLastName() {
	    return lastName;
	}
	public void setLastName(String lastName) {
	    this.lastName = lastName;
	}
	public String getPhone() {
	    return phone;
	}
	public void setPhone(String phone) {
	    this.phone = phone;
	}
	public String getAddress() {
	    return address;
	}
	public void setAddress(String address) {
	    this.address = address;
	}
	public String getCity() {
	    return city;
	}
	public void setCity(String city) {
	    this.city = city;
	}
	public String getZipCode() {
	    return zipCode;
	}
	public void setZipCode(String zipCode) {
	    this.zipCode = zipCode;
	}
	public String getEmail() {
	    return email;
	}
	public void setEmail(String email) {
	    this.email = email;
	}
	
	

}
