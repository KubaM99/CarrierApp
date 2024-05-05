package com.example.App.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.App.dto.AppMessage;
import com.example.App.dto.DeliveryForCarrierDTO;
import com.example.App.dto.DeliveryResponseDTO;
import com.example.App.repo.DeliveryRepo;
import com.example.App.service.CustomerService;
import com.example.App.service.DeliveryService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = DeliveryController.class)
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
@Import(DeliveryController.class)
class DeliveryControllerTest {
    
    @Autowired
    private MockMvc mockMvc;


    	@MockBean
    	private DeliveryService deliveryService;
    	@MockBean
	private DeliveryRepo deliveryRepo;
    	@MockBean
	private CustomerService customerService;
    
    
    	
    	@Autowired
  	private WebApplicationContext webApplicationContext;
      
      @Before
      public void before() {
            this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).dispatchOptions(true).build();

      }

    @Test
    @WithMockUser(username = "a@w.pl", password = "12345", authorities ={"CARRIER","CUSTOMER"})
    void testAddDelivery() throws Exception {
	
	AppMessage meg = new AppMessage("Carrier created.");
	
	ResultActions result = mockMvc.perform(get("/delivery/createDelivery")
		.contentType(MediaType.APPLICATION_JSON));
	
	result.andExpect(MockMvcResultMatchers.status().isCreated())
			.andExpect(MockMvcResultMatchers.jsonPath("$.massage1", CoreMatchers.is(meg.getMassage1())));
	
	
	
    }

    @Test
    @WithMockUser(username = "a@w.pl", password = "12345", authorities ={"CARRIER","CUSTOMER"})
    void testDeliveryForCarriers() throws Exception {
	
	List<DeliveryForCarrierDTO> deliveris = 
		new ArrayList<DeliveryForCarrierDTO>();
	
	deliveris.add(new DeliveryForCarrierDTO());
	deliveris.add(new DeliveryForCarrierDTO());
	
	when(deliveryService.deliverisForCarriers()).thenReturn(deliveris);
	
	ResultActions result = mockMvc.perform(get("/delivery/forCarrier")
		.contentType(MediaType.APPLICATION_JSON));
	
	result.andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @WithMockUser(username = "a@w.pl", password = "12345", authorities ={"CARRIER","CUSTOMER"})
    void testDeliveryForCustomer() throws Exception {
	
	List<DeliveryResponseDTO> deliveris = 
		new ArrayList<DeliveryResponseDTO>();
	
	deliveris.add(new DeliveryResponseDTO());
	deliveris.add(new DeliveryResponseDTO());
	
	when(deliveryService.customerDeliveries()).thenReturn(deliveris);
	
	ResultActions result = mockMvc.perform(get("/delivery/forCustomer")
		.contentType(MediaType.APPLICATION_JSON));
	
	result.andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Configuration
    @EnableWebMvc
    static class WebConfig implements WebMvcConfigurer {
    }
}
