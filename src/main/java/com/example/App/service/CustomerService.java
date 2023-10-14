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
public class CustomerService implements UserDetailsService {

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

	public void delteleCustomer(String email) {

		customerRepo.deleteByEmail(email);

	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	    
	    
	    System.out.println("user details");
	    
	    if(!username.equals("xyz@com.pl"))
		throw new UsernameNotFoundException("not found");
	    
	    Set<Role> rols = new HashSet<>();
	    rols.add(new Role(1L,"USER"));
	    
		return new Customer(1L,"xyz@com.pl",encoder.encode("pssword"),rols);
	}

}
