package com.example.App.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.example.App.model.Carrier;
import com.example.App.model.Delivery;

public interface DeliveryRepo extends JpaRepository<Delivery, Long> {

	//@Modifying
	//@Query("DELETE Delivery d WHERE d.customer.id =?1")
	//void deleteDeliveryByCustomerId(Long id);
	
	
	//List<Delivery> findAllDeliveryByCustomerId(Long id);
	
	Optional<Delivery> findById(Long id);
	

	@Query("SELECT d FROM Delivery d WHERE d.took = FALSE")
	List<Delivery> findAllDeliveryForCarriers();
	
	
	@Query("SELECT d FROM Delivery d WHERE d.id = ?1 AND d.took = FALSE")
	Optional<Delivery> findDeliveryForCarrier(Long id);
	
	
	Optional<List<Delivery>> findAllDeliveryByCarrierId(Long id);
	
	
}
