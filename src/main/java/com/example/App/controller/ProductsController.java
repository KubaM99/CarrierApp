package com.example.App.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.App.model.Product;
import com.example.App.service.ProductService;

@RestController
public class ProductsController {

	@Autowired
	private ProductService productService;
	
	@GetMapping("/getAllProduct")
	public ResponseEntity<List<Product>> getAllProductus(){
	
		List<Product> productList = productService.getAllProuct();

        return ResponseEntity.ok(productList);
	}
}
