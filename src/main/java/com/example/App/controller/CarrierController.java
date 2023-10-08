package com.example.App.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.App.dto.AddCarrierGetDTO;
import com.example.App.dto.AddCarrierPostDTO;
import com.example.App.dto.CarrierPostDTO;
import com.example.App.model.Carrier;
import com.example.App.model.CarrierDelivery;
import com.example.App.model.Customer;
import com.example.App.model.Delivery;
import com.example.App.repo.DeliveryRepo;
import com.example.App.service.CarrierDeliveryService;
import com.example.App.service.CarrierService;
import com.example.App.service.DeliveryService;

import jakarta.validation.Valid;
import lombok.experimental.var;

@RestController
public class CarrierController {
	
	
	@Autowired
	private DeliveryService deliveryService;
	@Autowired
	private CarrierService carrierService;
	@Autowired
	private CarrierDeliveryService carrierDeliveryService;
	
	
	
	@GetMapping("/getAllCarrier")
	public ResponseEntity<List<Carrier>> getAllCarrier(){
		
		List<Carrier> carriers = carrierService.findAllCarrier() ;
		
		return ResponseEntity.ok(carriers);
		
	}
	@PostMapping("/addCarrier")
	public  ResponseEntity<AddCarrierGetDTO> addCarrier(@Valid @RequestBody AddCarrierPostDTO carrierDTO){
		
		var acgd = new AddCarrierGetDTO();
		
			
			var c = new Carrier(			
				carrierDTO.getFirstName(),					
				carrierDTO.getLastName(),
				carrierDTO.getPhone(),
				carrierDTO.getAddress(),
				carrierDTO.getCity(),
				carrierDTO.getZipCode(),
				carrierDTO.getEmail(),
				carrierDTO.getPassword());
		
		
		
			acgd.setFirstName(carrierDTO.getFirstName());
			acgd.setLastName(carrierDTO.getLastName());
			acgd.setEmail(carrierDTO.getEmail());
			
		carrierService.saveCarrier(c);

			
		
				
		return  ResponseEntity.ok(acgd);
		
		
	}
	

	
	@PostMapping("/bookingDeliveryByCarrier")
	public  ResponseEntity<CarrierDelivery> bookingDeliveryByCarrier(@RequestBody CarrierPostDTO carrierPostDTO){
		
		Optional<Delivery> delivery  = deliveryService.findById(carrierPostDTO.getDeliveryId());
		//findById(delivery)
		
		Optional<Carrier> carrier = carrierService.findById(carrierPostDTO.getCarrierId());
		//findById(delivery)
		
		CarrierDelivery carrierDelivery = new CarrierDelivery(carrier.get(),delivery.get());
		
		carrierDeliveryService.save(carrierDelivery);
		
		
		
		
		return ResponseEntity.ok(carrierDelivery);
	}
	

	
	
	

}
