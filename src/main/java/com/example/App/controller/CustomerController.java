package com.example.App.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.App.dto.CustomerGetDTO;
import com.example.App.dto.CustomerPostDTO;
import com.example.App.model.Customer;
import com.example.App.model.Delivery;
import com.example.App.repo.ProductDeliveryRepo;
import com.example.App.service.CustomerService;
import com.example.App.service.DeliveryService;
import com.example.App.service.ProductDeliveryService;

import jakarta.validation.Valid;
import lombok.experimental.var;

@RestController
public class CustomerController {
	
	
	@Autowired
	private CustomerService customerService;
	@Autowired
	private DeliveryService deliveryService;
	@Autowired
	private ProductDeliveryService productDeliveryService;
	
	@GetMapping("/getAllCustomers")
	public ResponseEntity<List<Customer>> getAllCustomers(){
		List<Customer> customers = customerService.getAllCustomers();
		return ResponseEntity.ok(customers);
			
	}
	
	@PostMapping("/addCustomer")
	public ResponseEntity<CustomerGetDTO> addCustom(@RequestBody @Valid CustomerPostDTO cpDTO ){
		
		var customerGetDTO = new CustomerGetDTO();
		
		var customer = new Customer(			
						cpDTO.getFirstName(),					
						cpDTO.getLastName(),
						cpDTO.getPhone(),
						cpDTO.getAddress(),
						cpDTO.getCity(),
						cpDTO.getZipCode(),
						cpDTO.getEmail(),
						cpDTO.getPassword());
		
		
		customerService.saveCustomer(customer);
		
		
		customerGetDTO.setFirstName(cpDTO.getFirstName());								
		customerGetDTO.setLastName(cpDTO.getFirstName());
		customerGetDTO.setPhone(cpDTO.getPhone());
		customerGetDTO.setAddress(cpDTO.getAddress());
		customerGetDTO.setCity(cpDTO.getCity());
		customerGetDTO.setZipCode(cpDTO.getZipCode());
		customerGetDTO.setEmail(cpDTO.getEmail());
		
		
		
		return ResponseEntity.ok(customerGetDTO);
	}
	
	
	@DeleteMapping(value = "/{email}")
	public ResponseEntity<String> delteleCustomer(@PathVariable("email") String email){
		
		
		var customer = customerService.findByEmail(email);
		var deliveris  = deliveryService.findDeliveryByCustomerId(customer.get().getId()); 
		
	
		for(Delivery d : deliveris) {
			productDeliveryService.deleteProductDeliveryByDeliveryId(d.getId());
		}
		deliveryService.deleteDeliveryByCustomerId(customer.get().getId());
		customerService.delteleCustomer(email);
		
		return ResponseEntity.ok("ok");
		
		
	}
	
	
	
}
