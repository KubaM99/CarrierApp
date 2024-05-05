package com.example.App.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.example.App.model.Carrier;
import com.example.App.model.Delivery;

import jakarta.transaction.Transactional;

public interface DeliveryRepo extends JpaRepository<Delivery, Long> {

	Optional<Delivery> findById(Long id);
	

	@Query("SELECT d FROM Delivery d WHERE d.took = FALSE")
	Optional<List<Delivery>> findAllDeliveriesForCarriers();
	
	
	@Query("SELECT d FROM Delivery d WHERE d.id = ?1 AND d.took = FALSE")
	Optional<Delivery> findDeliveryForCarrier(Long id);
	
	
	//@Mo
	
	@Query(value = "SELECT * FROM delivery WHERE customer_id = ?1",nativeQuery = true)
	Optional<List<Delivery>> findAllDeliveryByCustomerId(Long id);
	
	
	Optional<List<Delivery>> findAllDeliveryByCarrierId(Long id);
	
	
}
