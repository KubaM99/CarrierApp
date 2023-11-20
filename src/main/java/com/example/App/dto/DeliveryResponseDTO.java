package com.example.App.dto;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.App.maper.DeliveryMaper;
import com.example.App.model.Delivery;
import com.example.App.model.ProductDelivery;


public class DeliveryResponseDTO {
	
	private Long orderId;
	private double amount;
	private Date data;
	private List<ProductDeliveryDTO> productDeliveryDTOs;
	
	
		
	





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
	public void setData(Date date) {
		this.data = date;
	}
	
	public List<ProductDeliveryDTO> getProductDeliveryDTOs() {
		return productDeliveryDTOs;
	}
	
	public void setProductDeliveryDTOs(List<ProductDeliveryDTO> productDeliveryDTOs) {
		this.productDeliveryDTOs = productDeliveryDTOs;
	}
	
	
}
