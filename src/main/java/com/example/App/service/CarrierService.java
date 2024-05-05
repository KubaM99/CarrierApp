package com.example.App.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.example.App.dto.ProducstToPickUpByCarreir;
import com.example.App.exceptation.NotFoundDelivery;
import com.example.App.maper.DeliveryMaper;
import com.example.App.model.Carrier;
import com.example.App.model.Delivery;
import com.example.App.model.ProductDeliveris;
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
	    
	    Delivery delivery = deliveryRepo.findDeliveryForCarrier(id)
		    .orElseThrow(()-> new NotFoundDelivery("Delivery not found"));
	    
	    Carrier carrier = carrierRepo.findByEmail(authentService.findUser())
		    .orElseThrow(()-> new UsernameNotFoundException("User dont exsist"));
	    
	    
	    delivery.setTook(true);
	    delivery.setCarrier(carrier);;

	    return "Delivery was booked at id: "+ delivery.getId();
			
		
	}
	
	public Long delivered(Long id) {
	    
	    //Carrier carrier = carrierRepo.findByEmail(authentService.findUser()).orElseThrow();
	    
	    Delivery delivery = deliveryRepo.findById(id)
		    .orElseThrow(()-> new NotFoundDelivery("Delivery not found"));
	    
	    delivery.setDelivered(true);
	    
	    return delivery.getId();
	    
	}
	
	public List<ProducstToPickUpByCarreir> listOfProdustsForCarreir() {
	    
	    Carrier carrier = carrierRepo.findByEmail(authentService.findUser()).orElseThrow();
	    
	    List<Delivery>  deliveries = deliveryRepo.findAllDeliveryByCarrierId(carrier.getId())
		    .orElseThrow(()-> new NotFoundDelivery("Delivery not found"));
	    
	    List<ProducstToPickUpByCarreir> productToPickUp = new ArrayList<ProducstToPickUpByCarreir>();
	    
	    
	    for (Delivery d : deliveries) {
		
		ProducstToPickUpByCarreir p = new ProducstToPickUpByCarreir();
		p.setOrderId(d.getId());
		
		
		List<ProductDeliveris> items = productDeliveryRepo.
			findProductDeliveryByDeliveryId(d.getId()).get();
		
		p.setProductDeliveryDTOs(DeliveryMaper.toDTO(items));
		
		productToPickUp.add(p);
	    }
	    
	    
	    
	    
	    return productToPickUp ;
	    
	}
	
	
	

}
