package com.example.App.service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.App.dto.AddProdacutToCardDTO;
import com.example.App.model.AppUser;
import com.example.App.model.Card;
import com.example.App.model.Customer;
import com.example.App.model.Product;
import com.example.App.model.ProductDelivery;
import com.example.App.repo.CardRepo;
import com.example.App.repo.CustomerRepo;
import com.example.App.repo.ProductRepo;
import com.example.App.repo.UserRepo;
import com.example.App.security.AuthentService;

@Component
public class CardService {
    
    @Autowired
    private ProductRepo productRepo;
    @Autowired
    private CardRepo cardRepo;
    @Autowired
    private AuthentService authentService;
    @Autowired
    private CustomerRepo customerRepo;
    @Autowired
    private UserRepo user;
    
    
    
    public List<ProductDelivery> addProductsToCard(List<AddProdacutToCardDTO> skus){
	
	var  customer = customerRepo.findByEmail(authentService.findUser()).orElseThrow();
	
	List<ProductDelivery> dp = new ArrayList<>();
	
	
        
	for (AddProdacutToCardDTO x : skus) {
        
		Product product = productRepo.findBySku(x.getSku()).orElseThrow();
        
		
		Long sku = product.getSku();
		String productName = product.getName();
		double price = product.getPrice();
			
        
		dp.add(new ProductDelivery(sku, productName, price, 1));
		
	}
	
	
	for(ProductDelivery product : dp) {
	    cardRepo.save(new Card(customer,product.getSku(),product.getProductName(),product.getPrice()));
	}
	
	
	return dp;
	
	
    }

}
 