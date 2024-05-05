package com.example.App.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.App.model.Customer;
import com.example.App.model.Delivery;
import com.example.App.model.Product;
import com.example.App.model.ProductDeliveris;
import com.example.App.repo.DeliveryRepo;
import com.example.App.repo.ProductDeliveryRepo;
import com.example.App.repo.ProductRepo;

@ExtendWith(MockitoExtension.class)
class ProductDeliveryServiceTest {
    
    
    @Mock
    private ProductDeliveryRepo productDeliveryRepo;

    @Mock
    private ProductRepo productRep;
    @Mock
    private DeliveryRepo deliveryRepo;
    
    @InjectMocks
    private ProductDeliveryService productDeliveryService;
    
    
    List<ProductDeliveris> list= new ArrayList<ProductDeliveris>();
    Delivery delivery = new Delivery(1L);
    
    @BeforeEach
    void setUp() {
	
	
	//Customer customer = new Customer("JAN", "jankowalski@abc.pl");

	//delivery = new Delivery(1L);
	

	ProductDeliveris pd1 = new ProductDeliveris(1111L, "ABC", 10, 1);

	ProductDeliveris pd2 = new ProductDeliveris(1111L, "XYZ", 10, 1);
	
	
	
	//Product product1 = new Product("XXX", 111L, 11);
	//Product product2 = new Product("XYZ", 222L, 12);
	
	
	list.add(pd1);
	list.add(pd2);
	
    }
    

    @Test
    void testGetProductListOfProductDeliverisDelivery() {
	
	when(productDeliveryRepo.findAll()).thenReturn(list);
	
	var result = productDeliveryService.getAllDeliveryProducts().size();
	
	assertThat(result).isNotNull();
	assertEquals(2, result);
    }

    @Test
    void testGetTotalprice() {
	var result = productDeliveryService.getTotalprice(list);
	
	assertThat(result).isNotNull();
	assertEquals(20, result);
    }


}
