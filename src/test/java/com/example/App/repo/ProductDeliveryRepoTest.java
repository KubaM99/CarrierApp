package com.example.App.repo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.mapping.Array;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.App.model.AppUser;
import com.example.App.model.Customer;
import com.example.App.model.Delivery;
import com.example.App.model.ProductDeliveris;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class ProductDeliveryRepoTest {

    @Autowired
    private ProductDeliveryRepo productDeliveryRepo;
    @Autowired
    private DeliveryRepo deliveryRepo;
    @Autowired
    private CustomerRepo customerRepo;
    
    @Test
    void testFindProductDeliveryByDeliveryId() {
	

	Customer customer = new Customer(
		    "JAN",
		    "jankowalski@abc.pl");
	
	customerRepo.save(customer);
	
	Delivery delivery =
		new Delivery(customer);
	
	deliveryRepo.save(delivery);
	
	ProductDeliveris pd1 = 
		new ProductDeliveris(1111L, "ABC", 10, 1, delivery );
	
	ProductDeliveris pd2 = 
		new ProductDeliveris(1111L, "XYZ", 10, 1, delivery );
	
	ProductDeliveris pd3 = 
		new ProductDeliveris(1111L, "QWE", 10, 1, delivery );
	
	List<ProductDeliveris> pdList = new ArrayList<>();
	
	pdList.add(pd1);
	pdList.add(pd2);
	pdList.add(pd3);
	
	productDeliveryRepo.saveAll(pdList);
	
	var productDeliverisList = productDeliveryRepo.findProductDeliveryByDeliveryId(delivery.getId()).get();
	
	
	assertThat(productDeliverisList).isNotNull();
	assertThat(productDeliverisList.size()).isEqualTo(3);
	
	
    }

}
