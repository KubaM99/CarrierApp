package com.example.App.service;

import static org.hamcrest.CoreMatchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.management.RuntimeErrorException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.repository.CrudRepository;

import com.example.App.dto.ProductDeliveryDTO;
import com.example.App.model.Card;
import com.example.App.model.Carrier;
import com.example.App.model.Customer;
import com.example.App.model.Product;
import com.example.App.repo.CardRepo;
import com.example.App.repo.CustomerRepo;
import com.example.App.repo.ProductRepo;
import com.example.App.security.AuthentService;

@ExtendWith(MockitoExtension.class)
class CardServiceTest {

    @Mock
    private AuthentService authentService;
    @Mock
    private CustomerRepo customerRepo;
    @Mock
    private ProductRepo productRepo;
    @Mock
    private CardRepo cardRepo;

    @InjectMocks
    private CardService cardService;

    @Test
    void testAddProductsToCard() {

	Customer customer = new Customer("JAN", "jankowalski@abc.pl");
	Product product = new Product("XXX", 111L, 11);
	Card card = new Card(customer, 111l, "XXX", 11);

	when(authentService.findUser()).thenReturn("jankowalski@abc.pl");
	when(customerRepo.findByEmail(customer.getEmail())).thenReturn(Optional.ofNullable(customer));
	when(productRepo.findBySku(product.getSku())).thenReturn(Optional.ofNullable(product));
	when(cardRepo.save(Mockito.any(Card.class))).thenReturn(card);

	cardService.addProductsToCard(111L);

	verify(cardRepo, times(1)).save(Mockito.any(Card.class));

    }

    @Test
    void testDeleteCartItem() {
	
	Customer customer = new Customer(1L, "JAN", "jankowalski@abc.pl");
	Card card = new Card(1L,customer,111L,"XXX",11);
	
	when(authentService.findUser()).thenReturn("jankowalski@abc.pl");
	when(customerRepo.findByEmail(customer.getEmail())).thenReturn(Optional.ofNullable(customer));
	
	when(cardRepo.findOneCardItem(customer.getId(), 111L)).thenReturn(Optional.ofNullable(card));
	
	cardService.deleteCartItem(111L);
	
	verify(cardRepo, times(1)).deleteCartItem(card.getId());
    }



}
