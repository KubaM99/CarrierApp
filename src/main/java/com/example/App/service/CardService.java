package com.example.App.service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.App.dto.AddProdacutToCardDTO;
import com.example.App.dto.ProductDeliveryDTO;
import com.example.App.exceptation.NotFoundProducts;
import com.example.App.model.AppUser;
import com.example.App.model.Card;
import com.example.App.model.Customer;
import com.example.App.model.Product;
import com.example.App.model.ProductDeliveris;
import com.example.App.repo.CardRepo;
import com.example.App.repo.CustomerRepo;
import com.example.App.repo.ProductRepo;
import com.example.App.repo.UserRepo;
import com.example.App.security.AuthentService;

import jakarta.transaction.Transactional;

@Component
@Transactional
public class CardService {
    
    @Autowired
    private ProductRepo productRepo;
    @Autowired
    private CardRepo cardRepo;
    @Autowired
    private AuthentService authentService;
    @Autowired
    private CustomerRepo customerRepo;
  
    
    
    
    public void addProductsToCard(Long sku){
	
	var  customer = customerRepo.findByEmail(authentService.findUser()).orElseThrow();
	
	
		Product product = productRepo.findBySku(sku)
			.orElseThrow(() ->new NotFoundProducts("Product not found"));
        
	
	    cardRepo.save(new Card(customer,product.getSku(),product.getName(),product.getPrice()));
    }
    
    
    public void deleteCartItem(Long sku){
	
	var  customer = customerRepo.findByEmail(authentService.findUser()).orElseThrow();
	var item = cardRepo.findOneCardItem(customer.getId(),sku)
		.orElseThrow(()-> new NotFoundProducts("Product not found"));
		
	
	cardRepo.deleteCartItem(item.getId().longValue());
	
    }
    
    public List<ProductDeliveryDTO> cardItemsForCustomer(){
	
	var  customer = customerRepo.findByEmail(authentService.findUser()).orElseThrow();
	var items = cardRepo.findByCustomerId(customer.getId()).orElseThrow(() ->new NotFoundProducts("Product not found"));
	
	List<ProductDeliveryDTO> c = new ArrayList<ProductDeliveryDTO>();
	
	for(Card i : items) {
	    
	    c.add(new ProductDeliveryDTO(i.getSku(),i.getProductName() ,i.getPrice()));
	}
	
	return c;
    }
    

}
 