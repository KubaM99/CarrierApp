package com.example.App.security;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.App.dto.AuthDTo;
import com.example.App.dto.CustomerGetDTO;
import com.example.App.dto.CustomerPostDTO;
import com.example.App.dto.LoginDTO;
import com.example.App.model.Carrier;
import com.example.App.model.Customer;
import com.example.App.model.Role;
import com.example.App.repo.CarrierRepo;
import com.example.App.repo.CustomerRepo;
import com.example.App.repo.RoleRepo;
import com.example.App.service.CarrierService;
import com.example.App.service.CustomerService;

@Component
public class AuthentService {

    @Autowired
    private CustomerService customerService;
    
    @Autowired
    private CarrierService carrierService;
    
    @Autowired
    private CustomerRepo customerRepo;
    
    @Autowired
    private CarrierRepo carrierRepo;
    
    @Autowired
    private PasswordEncoder encoder;
  
    
    @Autowired
    private RoleRepo roleRepo;
    
    @Autowired
    private JwtService jwtService;
    
    @Autowired
    private AuthenticationManager authenticationManager;
    
   
  
    
    public String registrationCustomer(CustomerPostDTO customerPostDTO) { //customerGetDto
	
	String passwordEncoded = encoder.encode(customerPostDTO.getPassword());
	
	var customer = new Customer(			
		customerPostDTO.getFirstName(),					
		customerPostDTO.getLastName(),
		customerPostDTO.getPhone(),
		customerPostDTO.getAddress(),
		customerPostDTO.getCity(),
		customerPostDTO.getZipCode(),
		customerPostDTO.getEmail(),
		passwordEncoded);
	
	
	Role customerRole = roleRepo.findByAuthority("CUSTOMER").get();
	Set<Role> role = new HashSet<>();
	role.add(customerRole);
	
	customer.setAuthoritis(role);

	customerRepo.save(customer);
	var jwtToken = jwtService.generateToken(customer);
	
	return jwtToken;
	
    }
    
    public String registrationCarrier(CustomerPostDTO carrierPostDTO) { //customerGetDto
	
	String passwordEncoded = encoder.encode(carrierPostDTO.getPassword());
	
	var carrier = new Carrier(
		carrierPostDTO.getFirstName(),					
		carrierPostDTO.getLastName(),
		carrierPostDTO.getPhone(),
		carrierPostDTO.getAddress(),
		carrierPostDTO.getCity(),
		carrierPostDTO.getZipCode(),
		carrierPostDTO.getEmail(),
		passwordEncoded);
	
	
	Role carrierRole = roleRepo.findByAuthority("CARRIER").get();
	Set<Role> role = new HashSet<>();
	role.add(carrierRole);
	
	//carrier.setAuthoritis(role);

	carrierService.saveCarrier(carrier);
	var jwtToken = jwtService.generateToken(carrier);
	
	return jwtToken;
	
    }
    
    public String loginCustomr(LoginDTO loginDTO) {

	
	authenticationManager.authenticate(
		new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword()));
	
	var customer = customerRepo.findByEmail(loginDTO.getEmail()).orElseThrow();
	
	var jwtToken = jwtService.generateToken(customer);
	
	return jwtToken;
	
	

	
    }
    
    public String loginCarrier(LoginDTO loginDTO) {

	
	authenticationManager.authenticate(
		new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword()));
	
	var carrier = carrierRepo.findByEmail(loginDTO.getEmail()).orElseThrow();
	
	var jwtToken = jwtService.generateToken(carrier);
	
	return jwtToken;
	
	

	
    }
}
