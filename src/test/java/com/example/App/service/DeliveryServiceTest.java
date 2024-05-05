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

import com.example.App.dto.DeliveryForCarrierDTO;
import com.example.App.dto.ProductDeliveryDTO;
import com.example.App.model.AppUser;
import com.example.App.model.Card;
import com.example.App.model.Customer;
import com.example.App.model.Delivery;
import com.example.App.model.Product;
import com.example.App.model.ProductDeliveris;
import com.example.App.repo.CustomerRepo;
import com.example.App.repo.DeliveryRepo;
import com.example.App.security.AuthentService;

@ExtendWith(MockitoExtension.class)
class DeliveryServiceTest {

    @Mock
    private DeliveryRepo deliveryRepo;
    @Mock
    private CustomerRepo customerRepo;
    @Mock
    private AuthentService authentService;
    @Mock
    private ProductDeliveryService productDeliveryService;

    @InjectMocks
    private DeliveryService deliveryService;

    @Test
    void testAddDelivery() {

    }

    @Test
    void testDeliverisForCarriers() {

	Customer custoemr = new Customer("JAN", "KOWALSKI");

	Delivery delivery = new Delivery(1L);
	Delivery delivery1 = new Delivery(2L);

	delivery.setCustomer(custoemr);
	delivery1.setCustomer(custoemr);

	List<Delivery> deliveries = new ArrayList<Delivery>();
	deliveries.add(delivery);
	deliveries.add(delivery1);

	when(deliveryRepo.findAllDeliveriesForCarriers()).thenReturn(Optional.ofNullable(deliveries));

	var result = deliveryService.deliverisForCarriers();

	assertThat(result).isNotNull();
	assertEquals(2, result.size());
    }

    @Test
    void testCustomerDeliveries() {

	Customer customer = new Customer("JAN", "jankowalski@abc.pl");
	customer.setId(1L);

	Delivery delivery = new Delivery(1L);
	Delivery delivery1 = new Delivery(2L);

	delivery.setCustomer(customer);
	delivery1.setCustomer(customer);

	List<Delivery> deliveries = new ArrayList<Delivery>();
	deliveries.add(delivery);
	deliveries.add(delivery1);

	var productDeliveryDTO = new ProductDeliveryDTO(111L, "XXX", 12);
	List<ProductDeliveryDTO> productDeliveryDTOs = new ArrayList<ProductDeliveryDTO>();
	productDeliveryDTOs.add(productDeliveryDTO);

	when(authentService.findUser()).thenReturn("jankowalski@abc.pl");

	when(customerRepo.findByEmail(customer.getEmail())).thenReturn(Optional.ofNullable(customer));
	when(deliveryRepo.findAllDeliveryByCustomerId(customer.getId())).thenReturn(Optional.ofNullable(deliveries));

	when(productDeliveryService.getProduct(delivery)).thenReturn(productDeliveryDTOs);

	var result = deliveryService.customerDeliveries();

	assertThat(result).isNotNull();
	assertEquals(2, result.size());

    }

}
