package com.example.App.model;

import java.util.Date;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SourceType;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "delivery")
public class Delivery {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	@ManyToOne
	@JoinColumn(name = "customer")
	private Customer customer;
	//@ManyToOne
	//@JoinColumn(name = "carrier")
	//private Carrier carrier;
	
	
	
	
	@CreationTimestamp(source = SourceType.DB)
	@Temporal(TemporalType.DATE)
	private Date createdDate;
	
	private double totalPris;
	
	//fetch = FetchType.LAZY
	@OneToMany(mappedBy = "delivery",cascade = CascadeType.ALL , orphanRemoval = true)
	private List<ProductDelivery> productDelivery;

	public Delivery(Customer customer) {
		super();
		this.customer = customer;
		
	}

	public Delivery() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public double getTotalPris() {
		return totalPris;
	}

	public void setTotalPris(Double totalPris) {
		this.totalPris = totalPris;
	}

	
	
	
	
	
	
	

}
