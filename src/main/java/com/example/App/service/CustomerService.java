package com.example.App.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.example.App.model.Customer;

import com.example.App.repo.CustomerRepo;
import com.example.App.repo.DeliveryRepo;

import jakarta.transaction.Transactional;
import lombok.experimental.var;

@Component
@Transactional
public class CustomerService {

	@Autowired
	private CustomerRepo customerRepo;
	




	public Customer saveCustomer(Customer customer) {
		return customerRepo.save(customer);
	}

	public List<Customer> getAllCustomers() {
		return customerRepo.findAll();
	}
	
	

	//public Optional<Customer> isCustomerPresent(Customer customer) {
    //
	//	Customer c = new Customer(customer.getFirstName(), customer.getEmail());
	//	return Optional.ofNullable(c);
    //
	//}
	
	public Optional<Customer> findByEmail(String email) {
		
		Customer  c = customerRepo.findByEmail(email);
		 
		return Optional.ofNullable(c);
	}
	
	
	public void delteleCustomer(String email) {
		
		customerRepo.deleteByEmail(email);
		
	}

	

}
