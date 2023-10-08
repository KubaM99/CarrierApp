package com.example.App.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.App.model.Carrier;
import com.example.App.repo.CarrierRepo;

import jakarta.transaction.Transactional;

@Component

@Transactional
public class CarrierService {
	
	@Autowired
	public CarrierRepo  carrierRepo;
	
	public Carrier saveCarrier(Carrier carrier) {
		return carrierRepo.save(carrier);
	}
	
	public List<Carrier> findAllCarrier() {
		return carrierRepo.findAll();
	}
	
	public Optional<Carrier> findById(Long id){
		
		Optional<Carrier> carrier = carrierRepo.findById(id);
		
		
		return Optional.ofNullable(carrier.get());
		
	}
	
	public boolean isEmailValid(String email) {
		
		Optional<Carrier> carrier = carrierRepo.findByEmail(email);
		
		if(carrier.isPresent()) return true;
		return false;
		
	}
	

}
