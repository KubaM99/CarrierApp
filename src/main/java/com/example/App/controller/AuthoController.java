package com.example.App.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.App.dto.CustomerPostDTO;
import com.example.App.dto.LoginDTO;
import com.example.App.repo.CarrierRepo;
import com.example.App.repo.CustomerRepo;
import com.example.App.security.AuthentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/authority")
public class AuthoController {

    @Autowired
    private CustomerRepo customerRepo;
    
    @Autowired
    private CarrierRepo carrierRepo;
    
    @Autowired
    private AuthentService authentService;
    
    
    
    @PostMapping("registerCustomer")
    public ResponseEntity<String> customerRegistration(@RequestBody @Valid CustomerPostDTO cpDTo){
	
	 if(customerRepo.existsByEmail(cpDTo.getEmail())) {
	     return new ResponseEntity<>("Customer just exsist",HttpStatus.BAD_REQUEST);
	 }
	
	return ResponseEntity.ok(authentService.registrationCustomer(cpDTo));
    }
    
    @PostMapping("registerCarrier")
    public ResponseEntity<String> carrierRegistration(@RequestBody @Valid CustomerPostDTO cpDTo){
	
	 if(carrierRepo.existsByEmail(cpDTo.getEmail())) {
	     return new ResponseEntity<>("Carrier just exsist",HttpStatus.BAD_REQUEST);
	 }
	
	return ResponseEntity.ok(authentService.registrationCarrier(cpDTo));
    }
    
    @PostMapping("/loginCustomer")
    public ResponseEntity<String> customerLogin(@RequestBody @Valid LoginDTO login){
	

	return ResponseEntity.ok(authentService.loginCustomr(login));
    }
    
    @PostMapping("/loginCarrier")
    public ResponseEntity<String> customerCarrier(@RequestBody @Valid LoginDTO login){
	

	return ResponseEntity.ok(authentService.loginCarrier(login));
    }
}
