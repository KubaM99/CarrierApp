package com.example.App.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.App.dto.AddProdacutToCardDTO;
import com.example.App.dto.DeliveryResponseDTO;
import com.example.App.dto.OrderDTO;
import com.example.App.service.CardService;
import com.example.App.service.CarrierService;

@RestController
@RequestMapping("/card")
public class CardController {
    
    
    @Autowired
    private CardService cardService;

    
    @PostMapping("/addProductsToCard")
    public ResponseEntity<String> addProductsToCard(@RequestBody List<AddProdacutToCardDTO> skus  ) {
	
	cardService.addProductsToCard(skus);
	
	return ResponseEntity.ok("ok");
	
	
	
    }
    
}
