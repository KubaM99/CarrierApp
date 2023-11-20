package com.example.App.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.App.model.ProductDelivery;
import com.example.App.model.Product;

public interface ProductRepo extends JpaRepository<Product, Long> {

	

	public Optional<Product> findBySku(Long sku);
	


}
