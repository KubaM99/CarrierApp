package com.example.App.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.App.model.Carrier;
import com.example.App.model.CarrierDelivery;
import com.example.App.service.CarrierDeliveryService;

@RestController
public class CarrierDEliveryController {
	
	private CarrierDeliveryService carrierDeliveryService;
	
	@PostMapping("/addOrderToCarrier")
	public  ResponseEntity<CarrierDelivery> addOrderToCarrier(@RequestBody CarrierDelivery carrierDelivery){
		
		CarrierDelivery c = carrierDeliveryService.save(carrierDelivery);
		
		
		return ResponseEntity.ok(c);
	}

}
