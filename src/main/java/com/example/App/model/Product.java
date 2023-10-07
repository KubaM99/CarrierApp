package com.example.App.model;



import jakarta.persistence.Entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.Table;




@Entity
@Table(name = "PRODUCTS")
public class Product {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	private String name;
	
	private Long sku;
	
	private int price;
	
	

	public Product(String name, Long sku) {
		super();
		this.name = name;
		this.sku = sku;
	}
	
	public Product(Long id,  String name, int price, Long sku) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.sku = sku;
	}
	
	public Product(Long id,  String name, int price) {
		this.id = id;
		this.name = name;
		this.price = price;
	
	}
	
	public Product(Long sku) {
		this.sku = sku;
	}
	


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public int getPrice() {
		return price;
	}


	public void setPrice(int price) {
		this.price = price;
	}
	
	
	

  //
//	public List<Cart> getCarts() {
//		return carts;
//	}
  //
  //
//	public void setCarts(List<Cart> carts) {
//		this.carts = carts;
//	}
  //
  //
	public Product() {
		super();
	}

	public Long getSku() {
		return sku;
	}

	public void setSku(Long sku) {
		this.sku = sku;
	}
	
	

}
