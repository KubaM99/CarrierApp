package com.example.App.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.App.dto.AppMessage;
import com.example.App.dto.CustomerPostDTO;
import com.example.App.dto.CustomerUpdateDTO;
import com.example.App.dto.LoginDTO;
import com.example.App.security.AuthentService;

import jakarta.persistence.PostUpdate;
import jakarta.validation.Valid;
import lombok.Data;

@RestController
@RequestMapping("/authority")
public class AuthoController {

    @Autowired
    private AuthentService authentService;
    
    

    @PostMapping("/registerCustomer")
    public ResponseEntity<AppMessage> customerRegistration(@RequestBody @Valid CustomerPostDTO cpDTo) {

	return new ResponseEntity<AppMessage>(new AppMessage("Customer created.",
		authentService.registrationCustomer(cpDTo), new Date()),
		HttpStatus.CREATED);
    }

    @PostMapping("/registerCarrier")
    public ResponseEntity<AppMessage> carrierRegistration(@RequestBody @Valid CustomerPostDTO cpDTo) {

	return new ResponseEntity<AppMessage>(new AppMessage("Carrier created.",
		authentService.registrationCarrier(cpDTo), new Date()),
		HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<AppMessage> customerLogin(@RequestBody @Valid LoginDTO login) throws Exception {

	return new ResponseEntity<AppMessage>(new AppMessage("User accepted.",
		authentService.loginUser(login), new Date()),
		HttpStatus.OK);

    }
    
    @PutMapping("/userUpdate")
    public ResponseEntity<AppMessage> updateUser(@RequestBody @Valid CustomerUpdateDTO user ){

	authentService.updateUser(user);
	
	return new ResponseEntity<AppMessage>(new AppMessage("User updated.", new Date()),
		HttpStatus.OK);

    }

}
