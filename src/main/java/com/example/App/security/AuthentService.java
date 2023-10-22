package com.example.App.security;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.App.dto.AuthDTo;
import com.example.App.dto.CustomerGetDTO;
import com.example.App.dto.CustomerPostDTO;
import com.example.App.dto.LoginDTO;
import com.example.App.model.Customer;
import com.example.App.model.Role;
import com.example.App.repo.CustomerRepo;
import com.example.App.repo.RoleRepo;

@Component
public class AuthentService {

    @Autowired
    private CustomerRepo customerRepo;
    
    @Autowired
    private PasswordEncoder encoder;
    
    @Autowired
    private RoleRepo roleRepo;
    
    @Autowired
    private JwtService jwtService;
    
    @Autowired
    private AuthenticationManager authenticationManager;
    
   
  
    
    public String registration(CustomerPostDTO customerPostDTO) { //customerGetDto
	
	var customerGetDTO = new CustomerGetDTO();
	
	
	
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
	
	
	Role customerRole = roleRepo.findByAuthority("USER").get();
	Set<Role> role = new HashSet<>();
	role.add(customerRole);
	
	customer.setAuthoritis(role);
	
	
	customerGetDTO.setFirstName(customerPostDTO.getFirstName());								
	customerGetDTO.setLastName(customerPostDTO.getLastName());
	customerGetDTO.setPhone(customerPostDTO.getPhone());
	customerGetDTO.setAddress(customerPostDTO.getAddress());
	customerGetDTO.setCity(customerPostDTO.getCity());
	customerGetDTO.setZipCode(customerPostDTO.getZipCode());
	customerGetDTO.setEmail(customerPostDTO.getEmail());
	
	customerRepo.save(customer);
	var jwtToken = jwtService.generateToken(customer);
	
	return jwtToken;
	
    }
    
    public String login(LoginDTO loginDTO) {
	
	Authentication authentication =  authenticationManager.authenticate(
		new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword()));
	
	var customer = customerRepo.findByEmail(loginDTO.getEmail()).orElseThrow();
	
	var jwtToken = jwtService.generateToken(customer);
	
	return jwtToken;
	
	

	
    }
}
