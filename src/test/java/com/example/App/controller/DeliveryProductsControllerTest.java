package com.example.App.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.App.model.ProductDeliveris;
import com.example.App.repo.ProductRepo;
import com.example.App.repo.TokenRepo;
import com.example.App.security.JwtService;
import com.example.App.service.ProductDeliveryService;
import com.fasterxml.jackson.databind.ObjectMapper;


@RunWith(SpringRunner.class)
@WebMvcTest(controllers = DeliveryProductsController.class)
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
@Import(DeliveryProductsController.class)
class DeliveryProductsControllerTest {
    
    @Autowired
    private MockMvc mockMvc;
    @Autowired
	private WebApplicationContext webApplicationContext;
    
    @Before
    public void before() {
          this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).dispatchOptions(true).build();

    }
    
    
    @MockBean
    private ProductDeliveryService productDeliveryService;

    @Test
    @WithMockUser(username = "a@w.pl", password = "12345", authorities ={"CARRIER","CUSTOMER"})
    void testGetAllDeliveryProducts() throws Exception {
	
	
	List<ProductDeliveris> list= new ArrayList<ProductDeliveris>(); ;
	
	list.add(new ProductDeliveris());
	list.add(new ProductDeliveris());
	
	when(productDeliveryService.getAllDeliveryProducts()).thenReturn(list);
	
	ResultActions result = mockMvc.perform(get("/")
		//.with(SecurityMockMvcRequestPostProcessors.user("a@w.pl").authorities())
		.contentType(MediaType.APPLICATION_JSON));
	
	result.andExpect(MockMvcResultMatchers.status().isOk());
	
	
	
	
	
    }

    @Configuration
    @EnableWebMvc
    static class WebConfig implements WebMvcConfigurer {
    }
}
