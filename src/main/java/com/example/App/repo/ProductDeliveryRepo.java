package com.example.App.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.App.model.ProductDeliveris;
import com.example.App.model.Delivery;
import com.example.App.model.Product;

public interface ProductDeliveryRepo extends JpaRepository<ProductDeliveris, Long> {

	//@Modifying
	//@Query("DELETE ProductDelivery d WHERE d.delivery.id =?1")
	//void deleteProductDeliveryByDeliveryId(Long id);
    
    
    	@Query("SELECT p FROM ProductDeliveris p WHERE p.delivery.id = :id")
    	Optional<List<ProductDeliveris>> findProductDeliveryByDeliveryId(@Param("id") Long id);
    	

    	
	
	

}
