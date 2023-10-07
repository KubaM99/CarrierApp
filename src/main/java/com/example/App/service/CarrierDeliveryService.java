package com.example.App.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.App.model.CarrierDelivery;
import com.example.App.repo.CarrierDeliveryRep9o;

import jakarta.transaction.Transactional;

@Component
@Transactional
public class CarrierDeliveryService {
	
	@Autowired
	private CarrierDeliveryRep9o carrierDeliveryRep9o;
	
	
	
	public CarrierDelivery save(CarrierDelivery cd) {
		
		return carrierDeliveryRep9o.save(cd);
	}

}
