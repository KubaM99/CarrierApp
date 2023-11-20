package com.example.App.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.App.model.Product;
import com.example.App.repo.ProductRepo;

@Component
public class ProductService {

	@Autowired
	private ProductRepo productRepo;
	
	public List<Product> getAllProuct(){
		return productRepo.findAll();
	}
	
}   
