package com.example.App.dto;

import java.sql.Date;
import java.util.Optional;

import com.example.App.model.Product;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor

public class ProductDeliveryDTO {

	private Long sku;
	String productName;
	private double price;
	

	
	
	public ProductDeliveryDTO(Long sku, String productName, double price) {
	    super();
	    this.sku = sku;
	    this.productName = productName;
	    this.price = price;
	}
	public Long getSku() {
		return sku;
	}
	public void setSku(Long sku) {
		this.sku = sku;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	
}
