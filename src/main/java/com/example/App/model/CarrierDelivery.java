package com.example.App.model;

import java.util.Optional;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
public class CarrierDelivery {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "customer")
	private Delivery delivery;
	@ManyToOne
	@JoinColumn(name = "carrier")
	private Carrier carrier;
	
	
	


	public CarrierDelivery(Carrier carrier, Delivery delivery) {
		super();
		this.carrier = carrier;
		this.delivery = delivery;
	}


	
	
	
	

}
