package com.example.App.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.App.model.Delivery;
import com.example.App.model.Product;
import com.example.App.repo.DeliveryRepo;
import com.example.App.repo.ProductRepo;
import com.example.App.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductsController {

	@Autowired
	private ProductRepo productRepo;
	
	
	@GetMapping("/getAllProducts")
	public ResponseEntity<List<Product>> getAllProductus(){
	
		List<Product> productList = productRepo.findAll();

        return ResponseEntity.ok(productList);
	}
	

	
}
