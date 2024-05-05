package com.example.App.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.aspectj.lang.annotation.Before;
import org.assertj.core.api.Assertions;
import org.hibernate.mapping.Array;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Example;

import com.example.App.model.Product;
import com.example.App.repo.ProductRepo;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {
    
    @Mock
    private ProductRepo productRepo;
    
    @InjectMocks
    private ProductService productService;

    List<Product> list= new ArrayList<Product>();
    
    @BeforeEach
    void setUp() {
	
	Product product1 = new Product("XXX", 111L, 11);
	Product product2 = new Product("XYZ", 222L, 12);
	
	
	list.add(product1);
	list.add(product2);
	
    }
    
    
    @Test
    void testGetAllProuct() {
	
	when(productRepo.findAll()).thenReturn(list);
	
	int result = productService.getAllProuct().size();
	
	assertThat(result).isNotNull();
	assertEquals(2, result);
	
    }

}
