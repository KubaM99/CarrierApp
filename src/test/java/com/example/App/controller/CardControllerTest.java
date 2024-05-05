package com.example.App.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.App.dto.DeliveryForCarrierDTO;
import com.example.App.dto.ProductDeliveryDTO;
import com.example.App.model.Card;
import com.example.App.repo.CardRepo;
import com.example.App.service.CardService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.transaction.Transactional;



@WebMvcTest(controllers = CardController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
@Import(CardController.class)
class CardControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CardService cardService;

    @MockBean
    private CardRepo cardrepo;

   // @Autowired
	//private WebApplicationContext webApplicationContext;
    
    
    //
    //@Before
    //public void before() {
    //      this.mockMvc = 
    //    	  MockMvcBuilders
    //    	  .webAppContextSetup(this.webApplicationContext)
    //    	  .apply(springSecurity())
    //    	  .build();
    //
    //}
    
    
    
    
    

    @Test
    @WithMockUser(username = "a@w.pl", password = "12345", authorities ={"CARRIER","CUSTOMER"})
    void testAddProductsToCard() throws Exception {
	
	doNothing().when(cardService).addProductsToCard(111L);
	
	ResultActions result = mockMvc.perform(post("/cart/addProductsToCard")
		.contentType(MediaType.APPLICATION_JSON)
		.content(new ObjectMapper().writeValueAsString(cardService)));
		
	
	result.andExpect(MockMvcResultMatchers.status().isOk());
	
    }

    
    
    
    @Test
    @WithMockUser(username = "a@w.pl", password = "12345", authorities ={"CARRIER","CUSTOMER"})
    void testDeleteProduct() throws Exception {
	
	doNothing().when(cardService).deleteCartItem(111L);
	
	ResultActions result = mockMvc.perform(delete("/cart//deleteProduct")
		.contentType(MediaType.APPLICATION_JSON)
		.content(new ObjectMapper().writeValueAsString(cardService)));
		
	
	result.andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @WithMockUser(username = "a@w.pl", password = "12345", authorities ={"CARRIER","CUSTOMER"})
    void testMyCard() throws Exception {
	
	List<ProductDeliveryDTO> pdDTO = 
		new ArrayList<ProductDeliveryDTO>();
	
	pdDTO.add(new ProductDeliveryDTO());
	pdDTO.add(new ProductDeliveryDTO());
	
	
	when(cardService.cardItemsForCustomer()).thenReturn(pdDTO);
	

	ResultActions result = mockMvc.perform(get("/cart/customerCard")
		.contentType(MediaType.APPLICATION_JSON));
		
	
	result.andExpect(MockMvcResultMatchers.status().isOk());
    }

   @Configuration
   @EnableWebMvc
    	static class WebConfig implements WebMvcConfigurer {
   }
}
