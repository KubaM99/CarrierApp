package com.example.App.dto.dalivery;

import java.util.Date;
import java.util.List;

import com.example.App.dto.ProductDeliveryDTO;
import com.example.App.model.AppUser;
import com.example.App.model.Delivery;

public class DeliveryForCarrierDTO {

    private Long orderId;
	private double amount;
	private Date data;
	private CustomerForCarrierDTO customer;
	

	
	
	public Long getOrderId() {
	    return orderId;
	}
	public void setOrderId(Long orderId) {
	    this.orderId = orderId;
	}
	public double getAmount() {
	    return amount;
	}
	public void setAmount(double amount) {
	    this.amount = amount;
	}
	public Date getData() {
	    return data;
	}
	public void setData(Date data) {
	    this.data = data;
	}
	
	public CustomerForCarrierDTO getCustomer() {
	    return customer;
	}
	public void setCustomer(CustomerForCarrierDTO customer) {
	    this.customer = customer;
	}
	
	
	
}
