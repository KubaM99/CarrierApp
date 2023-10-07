package com.example.App.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.App.model.Carrier;
import com.example.App.model.Delivery;
import com.example.App.model.ProductDelivery;
import com.example.App.model.Product;
import com.example.App.repo.DeliveryRepo;
import com.example.App.repo.ProductDeliveryRepo;
import com.example.App.repo.ProductRepo;

import jakarta.transaction.Transactional;



@Component
@Transactional
public class DeliveryService {

	@Autowired
	private DeliveryRepo deliveryRepo;
	

	
	
	public Delivery saveDelivery(Delivery delivery) {
		return deliveryRepo.save(delivery);
	}
	
	public List<Delivery> getAllDelivery() {
		return deliveryRepo.findAll();
	}
	
	
	public double getTotalPrise(List<ProductDelivery> dp) {
		
		double totalprise = 0;
		
			for(ProductDelivery product : dp) {
				
				totalprise += product.getPrice();
			}
			
		return totalprise;
	}
	
	public void deleteDeliveryByCustomerId(Long id) {
		deliveryRepo.deleteDeliveryByCustomerId(id);
		
	}

	public List<Delivery> findDeliveryByCustomerId(Long id) {
		
		List<Delivery> deliveries = deliveryRepo.findAllDeliveryByCustomerId(id);
		
		//return deliveryRepo.findAllById(delivery.stream().map(Delivery :: getId).collect(Collectors.toList()));
		return deliveries;
	}
	
	
	public Optional<Delivery> findById(Long id){
		
		Optional<Delivery> dleivery = deliveryRepo.findById(id);
		
		
		return Optional.ofNullable(dleivery.get());
		
	}


	
		
		
	

}

