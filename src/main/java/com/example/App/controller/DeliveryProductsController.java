package com.example.App.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.App.model.ProductDelivery;
import com.example.App.service.ProductDeliveryService;

@RestController
public class DeliveryProductsController {

	@Autowired
	private ProductDeliveryService productDeliveryService;
	
	@GetMapping("/getAllDeliveryProducts")
	public ResponseEntity<List<ProductDelivery>> getAllDeliveryProducts(){
		List<ProductDelivery> dp = productDeliveryService.getAllDeliveryProducts();
		return ResponseEntity.ok(dp);
			
	}
}
