
package com.example.App.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.App.dto.ProducstToPickUpByCarreir;
import com.example.App.maper.DeliveryMaper;
import com.example.App.model.Carrier;
import com.example.App.model.Customer;
import com.example.App.model.Delivery;
import com.example.App.model.ProductDeliveris;
import com.example.App.repo.CarrierRepo;
import com.example.App.repo.DeliveryRepo;
import com.example.App.repo.ProductDeliveryRepo;
import com.example.App.security.AuthentService;

@ExtendWith(MockitoExtension.class)
class CarrierServiceTest {

    @Mock
    private AuthentService authentService;
    @Mock
    public CarrierRepo  carrierRepo;
    @Mock
    private DeliveryRepo deliveryRepo;
    @Mock
    private ProductDeliveryRepo productDeliveryRepo;
    
    
    @InjectMocks
    private CarrierService carrierService;
    
    @Test
    void testBookingDelivery() {
	
	Delivery delivery = new Delivery(1l);
	
	Carrier carrire = new Carrier("JAN", "jankowalski@abc.pl");
	
	
	when(deliveryRepo.findDeliveryForCarrier(delivery.getId())).thenReturn(Optional.ofNullable(delivery));
	when(authentService.findUser()).thenReturn("jankowalski@abc.pl");
	
	when(carrierRepo.findByEmail(carrire.getEmail())).thenReturn(Optional.ofNullable(carrire));
	
	
	var result = carrierService.bookingDelivery(delivery.getId());
	
	System.out.print(result);
	
	assertThat(result).isNotNull();
	assertEquals("Delivery was booked at id: 1", result);
	
	
    }

    @Test
    void testDelivered() {

	Delivery delivery = new Delivery(1l);
	
	when(deliveryRepo.findById(delivery.getId())).thenReturn(Optional.ofNullable(delivery));
	
	var result = carrierService.delivered(delivery.getId());
	
	assertThat(result).isNotNull();
	assertThat(result).isGreaterThan(0);
	
	System.out.print(result);
	
	assertEquals(1L, result);
    
    }

//   @Test
//   void testListOfProdustsForCarreir() {
//	
//	Carrier carrire = new Carrier("JAN", "jankowalski@abc.pl");
//	//carrire.setId(1l);
//	
//	Delivery delivery = new Delivery(1L);
//	delivery.setCarrier(carrire);
//	Delivery delivery1 = new Delivery(2L);
//	delivery1.setCarrier(carrire);
//
//	List<Delivery> deliveries = new ArrayList<Delivery>();
//	deliveries.add(delivery);
//	deliveries.add(delivery1);
//	
//	List<ProductDeliveris> productDeliveris = new ArrayList<ProductDeliveris>();
//	productDeliveris.add( new ProductDeliveris(1111L, "ABC", 10, 1));
//	productDeliveris.add( new ProductDeliveris(1111L, "XYZ", 10, 1));
//	
//	ProducstToPickUpByCarreir p = new ProducstToPickUpByCarreir();
//	
//	
//	when(authentService.findUser()).thenReturn("jankowalski@abc.pl");
//	System.out.println("---");
//	
//	when(carrierRepo.findByEmail(carrire.getEmail())).thenReturn(Optional.ofNullable(carrire));
//  
//	when(deliveryRepo.findAllDeliveryByCarrierId(carrire.getId())).thenReturn(Optional.ofNullable(deliveries));
//	System.out.println("---");
//	when(productDeliveryRepo.findProductDeliveryByDeliveryId(delivery.getId())).thenReturn(Optional.ofNullable(productDeliveris));
//   
//	System.out.println("---");
//	
//	p.setProductDeliveryDTOs(DeliveryMaper.toDTO(productDeliveris));
//	
//	
//	var result = carrierService.listOfProdustsForCarreir();
//	
//	
//	
//	System.out.print(result);
//   
//   }

}
