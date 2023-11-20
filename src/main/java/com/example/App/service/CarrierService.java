package com.example.App.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.App.dto.ProducstToPickUpByCarreir;
import com.example.App.maper.DeliveryMaper;
import com.example.App.model.Carrier;
import com.example.App.model.Delivery;
import com.example.App.model.ProductDelivery;
import com.example.App.repo.CarrierRepo;
import com.example.App.repo.DeliveryRepo;
import com.example.App.repo.ProductDeliveryRepo;
import com.example.App.security.AuthentService;

import jakarta.transaction.Transactional;

@Component

@Transactional
public class CarrierService {
	
	@Autowired
	public CarrierRepo  carrierRepo;
	@Autowired
	private DeliveryRepo deliveryRepo;
	
	@Autowired
	private ProductDeliveryRepo productDeliveryRepo;
	@Autowired
	private AuthentService authentService;
	
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
	
	public String bookingDelivery(Long id) {
		//()-> new RuntimeException("Delivery not exsist")
	    Delivery delivery = deliveryRepo.findDeliveryForCarrier(id).orElseThrow();
	    
	    Carrier carrier = carrierRepo.findByEmail(authentService.findUser()).orElseThrow();
	    
	    //carrierDeliveryRepo.save(new CarrierDelivery(carrier,delivery));
	    
	    delivery.setTook(true);
	    delivery.setCarrier(carrier);;

	    return "Delivery has been booked at id: "+ delivery.getId();
		
		
		
	}
	
	public Long delivered(Long id) {
	    
	    Carrier carrier = carrierRepo.findByEmail(authentService.findUser()).orElseThrow();
	    
	    Delivery delivery = deliveryRepo.findById(id).orElseThrow();
	    
	    delivery.setDelivered(true);
	    
	    return delivery.getId();
	    
	}
	
	public List<ProducstToPickUpByCarreir> yyy() {
	    
	    Carrier carrier = carrierRepo.findByEmail(authentService.findUser()).orElseThrow();
	    
	    List<Delivery>  deliveries = deliveryRepo.findAllDeliveryByCarrierId(carrier.getId()).orElseThrow();
	    
	    List<ProducstToPickUpByCarreir> productToPickUp = new ArrayList<ProducstToPickUpByCarreir>();
	    
	    
	    for (Delivery d : deliveries) {
		
		ProducstToPickUpByCarreir p = new ProducstToPickUpByCarreir();
		p.setOrderId(d.getId());
		
		
		List<ProductDelivery> items = productDeliveryRepo.findProductDeliveryByDeliveryId(d.getId()).get();
		p.setProductDeliveryDTOs(DeliveryMaper.toDTO(items));
		
		productToPickUp.add(p);
	    }
	    
	    
	    
	    
	    return productToPickUp ;
	    
	}
	
	
	

}
