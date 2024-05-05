package com.example.App.dto;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.App.maper.DeliveryMaper;
import com.example.App.model.Delivery;
import com.example.App.model.ProductDeliveris;


public class DeliveryResponseDTO {
	
	private Long orderId;
	private double amount;
	private Date data;
	private boolean delivered;
	private boolean took;
	private List<ProductDeliveryDTO> productDeliveryDTOs;
	
	
	public DeliveryResponseDTO() {};

	public DeliveryResponseDTO(Long orderId, double amount, Date data,
		List<ProductDeliveryDTO> productDeliveryDTOs) {
	    super();
	    this.orderId = orderId;
	    this.amount = amount;
	    this.data = data;
	    this.productDeliveryDTOs = productDeliveryDTOs;
	}
	
	
	
	public DeliveryResponseDTO(Long orderId, double amount, Date data, boolean delivered, boolean took,
		List<ProductDeliveryDTO> productDeliveryDTOs) {
	    super();
	    this.orderId = orderId;
	    this.amount = amount;
	    this.data = data;
	    this.delivered = delivered;
	    this.took = took;
	    this.productDeliveryDTOs = productDeliveryDTOs;
	}

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

	public boolean isDelivered() {
	    return delivered;
	}

	public void setDelivered(boolean delivered) {
	    this.delivered = delivered;
	}

	public boolean isTook() {
	    return took;
	}

	public void setTook(boolean took) {
	    this.took = took;
	}
	
	
	
}
