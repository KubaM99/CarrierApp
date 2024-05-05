package com.example.App.dto;

import java.util.List;

import org.springframework.stereotype.Component;

import com.example.App.model.ProductDeliveris;


public class OrderDTO {

	private String customerEmail;
	private String customerName;
	private List<ProductDeliveris> productDeliveris;
	

	public String getCustomerEmail() {
		return customerEmail;
	}



	public String getCustomerName() {
		return customerName;
	}


	public List<ProductDeliveris> getProductDeliveris() {
		return productDeliveris;
	}
	
	
                                                                             
	public void setProductDeliveris(List<ProductDeliveris> deliveryProducts) { 
		this.productDeliveris = deliveryProducts;                                
	}                                                                        
                                                                             
	public void setCustomerEmail(String customerEmail) {                     
		this.customerEmail = customerEmail;                                    
	}


	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}


                                                                     
	
	
}
