package com.example.App.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.example.App.model.Customer;
import com.example.App.model.Role;
import com.example.App.repo.CustomerRepo;
import com.example.App.repo.DeliveryRepo;

import jakarta.transaction.Transactional;
import lombok.experimental.var;

@Component
@Transactional
public class CustomerService  {

	@Autowired
	private CustomerRepo customerRepo;
	
	@Autowired
	private PasswordEncoder encoder;

	public Customer saveCustomer(Customer customer) {
		return customerRepo.save(customer);
	}

	public List<Customer> getAllCustomers() {
		return customerRepo.findAll();
	}

	public Optional<Customer> findByEmail(String email) {

		Customer c = customerRepo.findByEmail(email).get();

		return Optional.ofNullable(c);
	}

	//public void delteleCustomer(String email) {
        //
	//	customerRepo.deleteByEmail(email);
        //
	//}

	    
	//ername.equals("xyz@com.pl"))
	//row new UsernameNotFoundException("not found");
	//
	//le> rols = new HashSet<>();
	//dd(new Role(1L,"USER"));
	//
	//turn new Customer(1L,"xyz@com.pl",encoder.encode("pssword"),rols);
}


