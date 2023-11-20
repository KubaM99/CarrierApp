package com.example.App.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.App.dto.CustomerGetDTO;
import com.example.App.dto.CustomerPostDTO;
import com.example.App.model.AppUser;
import com.example.App.model.Customer;
import com.example.App.model.Delivery;
import com.example.App.repo.ProductDeliveryRepo;
import com.example.App.repo.UserRepo;
import com.example.App.service.CustomerService;
import com.example.App.service.DeliveryService;
import com.example.App.service.ProductDeliveryService;

import jakarta.validation.Valid;
import lombok.experimental.var;

@RestController
@RequestMapping("/customer")
public class CustomerController {
	
	
	@Autowired
	private CustomerService customerService;
	@Autowired
	private DeliveryService deliveryService;
	@Autowired
	private ProductDeliveryService productDeliveryService;
	
	
	@GetMapping("/user")
	public ResponseEntity<AppUser> getUserInfo() {
	    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    AppUser user = (AppUser) authentication.getPrincipal();
	    return ResponseEntity.ok(user);
	}
	
	@GetMapping("/getAllCustomers")
	public ResponseEntity<List<Customer>> getAllCustomers(){
		List<Customer> customers = customerService.getAllCustomers();
		return ResponseEntity.ok(customers);
			
	}
	
	
	
	
	//@DeleteMapping(value = "/{email}")
	//public ResponseEntity<String> delteleCustomer(@PathVariable("email") String email){
	//	
	//	
	//	var customer = customerService.findByEmail(email);
	//	var deliveris  = deliveryService.findDeliveryByCustomerId(customer.get().getId()); 
	//	
	//
	//	for(Delivery d : deliveris) {
	//		productDeliveryService.deleteProductDeliveryByDeliveryId(d.getId());
	//	}
	//	deliveryService.deleteDeliveryByCustomerId(customer.get().getId());
	//	customerService.delteleCustomer(email);
	//	
	//	return ResponseEntity.ok("ok");
	//	
	//	
	//}
	
	
	
}
