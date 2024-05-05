package com.example.App.dto;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class AddCarrierPostDTO {
	
	
	
	@NotBlank(message = "Name is mandatory")
	private String firstName;
	
	@NotBlank(message = "Lastname is mandatory")
	private String lastName;
	
	@Pattern(regexp = "^\\d{10}$",message = "Invalid phone number")
	private String phone;	
	
	@NotBlank(message = "Address is mandatory")
	private String address;
	
	@NotBlank(message = "City is mandatory")
	private String city;
	
	@NotBlank(message = "Post code is mandatory")
	private String zipCode;
	
	@Email(message = "Email is mandatory or not valid")
	private String email;
	
	@NotBlank(message = "Password is mandatory")
	@Length(min = 5, message = "Password is too short")
	private String password;
	
	
	
	//public CustomerPostDTO(String firstName, String lastName, String phone, String address, String city, String zipCode,
	//		String email, String password) {
	//	super();
	//	this.firstName = firstName;
	//	this.lastName = lastName;
	//	this.phone = phone;
	//	this.address = address;
	//	this.city = city;
	//	this.zipCode = zipCode;
	//	this.email = email;
	//	this.password = password;
	//}
	//
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
	
	

}
