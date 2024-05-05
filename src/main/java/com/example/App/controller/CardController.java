package com.example.App.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.App.dto.AddProdacutToCardDTO;
import com.example.App.dto.AppMessage;
import com.example.App.dto.DeliveryResponseDTO;
import com.example.App.dto.OrderDTO;
import com.example.App.dto.ProductDeliveryDTO;
import com.example.App.model.Card;
import com.example.App.repo.CardRepo;
import com.example.App.service.CardService;
import com.example.App.service.CarrierService;

@RestController
@RequestMapping("/cart")
public class CardController {
    
    
    @Autowired
    private CardService cardService;
    
    @Autowired
    private CardRepo cardrepo;

    
    @PostMapping("/addProductsToCard")
    public ResponseEntity<AppMessage> addProductsToCard(@RequestBody AddProdacutToCardDTO sku  ) {
	
	cardService.addProductsToCard(sku.getSku());
	
	return new ResponseEntity<AppMessage>(new AppMessage("Product: " + sku.getSku() +" added to cart",
		new Date()),
		HttpStatus.OK);
    }
    
    @DeleteMapping("/deleteProduct")
    public ResponseEntity<AppMessage> deleteProduct(@RequestBody AddProdacutToCardDTO sku  ) {
	
	cardService.deleteCartItem(sku.getSku());
	
	return new ResponseEntity<AppMessage>(new AppMessage("Product: " + sku.getSku() +" deleted from cart",
		new Date()),
		HttpStatus.OK);
	
    }
    
    @GetMapping("/customerCard")//@RequestBody AddProdacutToCardDTO sku 
    public ResponseEntity<List<ProductDeliveryDTO>> myCard( ) {
	
	var items = cardService.cardItemsForCustomer();
	
	return ResponseEntity.ok(items);
	
	
    }
    
}
