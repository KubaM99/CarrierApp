 package com.example.App.repo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.App.model.Customer;
import com.example.App.model.Delivery;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class DeliveryRepoTest {

    @Autowired
    private DeliveryRepo deliveryRepo;
    @Autowired
    private CustomerRepo customerRepo;
    
    @Test
    void testFindByIdLong() {
	Customer customer = new Customer(
		    "JAN",
		    "jankowalski@abc.pl");
	
	customerRepo.save(customer);
	
	Delivery delivery =
		new Delivery(customer);
	
	deliveryRepo.save(delivery);
	
	var result = deliveryRepo.findById(delivery.getId()).get();
	
	assertThat(result).isNotNull();
	
    }

    @Test
    void testFindAllDeliveriesForCarriers() {
	
	Customer customer = new Customer(
		    "JAN",
		    "jankowalski@abc.pl");
	
	customerRepo.save(customer);
	
	Delivery delivery1 =
		new Delivery(customer);
	Delivery delivery2 =
		new Delivery(customer);
	Delivery delivery3 =
		new Delivery(customer);
	
	deliveryRepo.save(delivery1);
	deliveryRepo.save(delivery2);
	deliveryRepo.save(delivery3);
	
	var result = deliveryRepo.findAllDeliveriesForCarriers().get();
	
	assertThat(result).isNotNull();
	assertThat(result.size()).isEqualTo(3);
	
    }

    @Test
    void testFindAllDeliveryByCustomerId() {
	Customer customer = new Customer(
		    "JAN",
		    "jankowalski@abc.pl");
	
	customerRepo.save(customer);
	
	Delivery delivery1 =
		new Delivery(customer);
	Delivery delivery2 =
		new Delivery(customer);
	Delivery delivery3 =
		new Delivery(customer);
	
	deliveryRepo.save(delivery1);
	deliveryRepo.save(delivery2);
	deliveryRepo.save(delivery3);
	
	var result = deliveryRepo.findAllDeliveryByCustomerId(customer.getId()).get();
	
	assertThat(result).isNotNull();
	assertThat(result.size()).isEqualTo(3);
    }

}
