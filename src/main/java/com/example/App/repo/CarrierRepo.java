package com.example.App.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.App.model.Carrier;
import com.example.App.model.Delivery;

public interface CarrierRepo  extends JpaRepository<Carrier, Long> {
	
	Optional<Carrier> findById(Long id);
	

	
	
}


