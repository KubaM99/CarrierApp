package com.example.App.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.App.model.ProductDelivery;
import com.example.App.model.Delivery;
import com.example.App.model.Product;

public interface ProductDeliveryRepo extends JpaRepository<ProductDelivery, Long> {

	//@Modifying
	//@Query("DELETE ProductDelivery d WHERE d.delivery.id =?1")
	//void deleteProductDeliveryByDeliveryId(Long id);
    
    
    	@Query("SELECT p FROM ProductDelivery p WHERE p.delivery.id = :product")
    	Optional<List<ProductDelivery>> findProductDeliveryByDeliveryId(@Param("product") Long list);
    	

	
	

}
