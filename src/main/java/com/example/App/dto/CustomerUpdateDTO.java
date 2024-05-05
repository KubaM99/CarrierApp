package com.example.App.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class CustomerUpdateDTO {
    
    	@NotBlank(message = "Name is mandatory")
	private String firstName;
	
	@NotBlank(message = "Lastname is mandatory")
	private String lastName;
	
	@Pattern(regexp = "^\\d{10}$",message = "Invalid phone number")
	private String phone;	
	
	@NotBlank(message = "Address is mandatory")
	private String address;
	
	@NotBlank(message = "city is mandatory")
	private String city;
	
	@NotBlank(message = "Post code is mandatory")
	private String zipCode;
		

}
