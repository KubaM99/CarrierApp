package com.example.App.model;

import java.sql.Date;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SourceType;
import org.springframework.data.annotation.CreatedDate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data


@Entity
//@Table(name = "DeliveryProducts")
@Table(name = "ProductDeliverys")
public class ProductDeliveris {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private int quantity;
	
	private double price;
	
	
	private Long sku;
	 
	private String productName;
	
	
	@CreationTimestamp()
	@Temporal(TemporalType.DATE)
	private Date createdDate;
	
	
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "product")
	private Product product;
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY )
	@JoinColumn(name = "delivery_id",referencedColumnName = "id")
	private Delivery delivery;
    
	public ProductDeliveris(int quantity, double price, Product product) {
		super();
		this.quantity = quantity;
		this.price = price;
		
		
	}
	
	




	public ProductDeliveris(Long sku, String productName, double price, Integer quantity, Delivery delivery) {
		super();
		this.sku=sku;
		this.productName=productName;
		this.price=price;
		this.quantity = quantity;
		this.delivery = delivery;
	}
	
	public ProductDeliveris(Long sku, String productName, double price, Integer quantity) {
		super();
		this.sku=sku;
		this.productName=productName;
		this.price=price;
		this.quantity = quantity;
		
	}
	
	





	public ProductDeliveris() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
    
	//public Product getProduct() {
	//	return product;
	//}
    //
	//public void setProduct(Product product) {
	//	this.product = product;
	//}
    
	public Delivery getOrder() {
		return delivery;
	}
    
	public void setOrder(Delivery delivery) {
		this.delivery = delivery;
	}	


	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}


	

	public Delivery getDelivery() {
		return delivery;
	}






	public void setDelivery(Delivery delivery) {
		this.delivery = delivery;
	}






	public Long getSku() {
		return sku;
	}






	public void setSku(Long sku) {
		this.sku = sku;
	}
	
	
	

}
