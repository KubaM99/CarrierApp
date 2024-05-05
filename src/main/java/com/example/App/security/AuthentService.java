package com.example.App.security;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.security.core.userdetails.User;

import com.example.App.dto.CustomerPostDTO;
import com.example.App.dto.CustomerUpdateDTO;
import com.example.App.dto.LoginDTO;
import com.example.App.exceptation.CustomerExsistExceptation;
import com.example.App.exceptation.ExceptatopnObject;
import com.example.App.exceptation.InvalidPasswordExceptation;
import com.example.App.exceptation.UserNotFoundExceptaion;
import com.example.App.model.AppUser;
import com.example.App.model.Carrier;
import com.example.App.model.Customer;
import com.example.App.model.Role;
import com.example.App.model.Token;
import com.example.App.model.TokenType;
import com.example.App.repo.CarrierRepo;
import com.example.App.repo.CustomerRepo;
import com.example.App.repo.RoleRepo;
import com.example.App.repo.TokenRepo;
import com.example.App.repo.UserRepo;

@Component
public class AuthentService {

    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private CarrierRepo carrierRepo;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    private RoleRepo roleRepo;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Autowired
    private TokenRepo tokenRepo;

    public String registrationCustomer(CustomerPostDTO customerPostDTO) {

	if (userRepo.findByEmail(customerPostDTO.getEmail()).isPresent()) {

	    throw new CustomerExsistExceptation("User just exist");
	}

	String passwordEncoded = encoder.encode(customerPostDTO.getPassword());

	var user = new AppUser(
		customerPostDTO.getFirstName(),
		customerPostDTO.getLastName(),
		customerPostDTO.getPhone(),
		customerPostDTO.getAddress(),
		customerPostDTO.getCity(),
		customerPostDTO.getZipCode(),
		customerPostDTO.getEmail(),
		passwordEncoded);

	var castomer = new Customer(
		customerPostDTO.getFirstName(),
		customerPostDTO.getLastName(),
		customerPostDTO.getPhone(),
		customerPostDTO.getAddress(),
		customerPostDTO.getCity(),
		customerPostDTO.getZipCode(),
		customerPostDTO.getEmail(),
		passwordEncoded, user);

	Role customerRole = roleRepo.findByAuthority("CUSTOMER").get();
	Set<Role> role = new HashSet<>();
	role.add(customerRole);

	user.setAuthoritis(role);

	var saveuser=userRepo.save(user);
	
	customerRepo.save(castomer);
	
	var jwtToken = jwtService.generateToken(user);

	saveAppUserToken(saveuser, jwtToken);
	
	return jwtToken;

    }



    public String registrationCarrier(CustomerPostDTO carrierPostDTO) {

	customerRepo.findByEmail(carrierPostDTO.getEmail())
		.orElseThrow(() -> new UserNotFoundExceptaion("You must first register as a customer"));

	String passwordEncoded = encoder.encode(carrierPostDTO.getPassword());

	var user = userRepo.findByEmail(carrierPostDTO.getEmail()).orElseThrow();

	var carrier = new Carrier(
		carrierPostDTO.getFirstName(),
		carrierPostDTO.getLastName(),
		carrierPostDTO.getPhone(),
		carrierPostDTO.getAddress(),
		carrierPostDTO.getCity(),
		carrierPostDTO.getZipCode(),
		carrierPostDTO.getEmail(),
		passwordEncoded, user);

	Role carrierRole = roleRepo.findByAuthority("CARRIER").get();
	Set<Role> role = user.getAuthoritis();
	role.add(carrierRole);

	user.setAuthoritis(role);

	userRepo.save(user);
	carrierRepo.save(carrier);
	var jwtToken = jwtService.generateToken(user);

	return jwtToken;

    }

    public String loginUser(LoginDTO loginDTO) throws Exception {
	
	
	var user = userRepo.findByEmail(loginDTO.getEmail())
		.orElseThrow(() -> new UserNotFoundExceptaion("User doesnt exsist"));
	
	if(!encoder.matches(loginDTO.getPassword(), user.getPassword())) {
	    throw new InvalidPasswordExceptation("Invalid password/login");
	}
	
	 authenticationManager
    		.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getEmail(),
    						loginDTO.getPassword()));
	
	
	var jwtToken = jwtService.generateToken(user);
	revokeAllTokens(user);
	saveAppUserToken(user,jwtToken);

	return jwtToken;
	   
	
    }

    public String findUser() {

	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	AppUser user = (AppUser) authentication.getPrincipal();

	return user.getEmail();
    }
    
    
    private void saveAppUserToken(AppUser appUser, String jwtToken) {
	
	var token = new Token();
	
	token.setAppUser(appUser);
	token.setToken(jwtToken);
	token.setTokenType(TokenType.BEARER);
	token.setRevoked(false);
	token.setExpired(false);
	
	
	
	tokenRepo.save(token);
    }
    
    private void revokeAllTokens(AppUser appUser) {

	var tokens = tokenRepo.findAllValidTokensByUser(appUser.getId());


	tokens.forEach(t -> {
	    t.setRevoked(true);
	    t.setExpired(true);
	});

	tokenRepo.saveAll(tokens);
    }
    
    public void updateUser(CustomerUpdateDTO appuser) {
	
	
	var user= userRepo.findByEmail(findUser())
		.orElseThrow(() -> new UserNotFoundExceptaion("User doesnt exsist"));
	var castomer = customerRepo.findByEmail(findUser())
		.orElseThrow(() -> new UserNotFoundExceptaion("User doesnt exsist"));
	var carrier= carrierRepo.findByEmail(findUser());

	user.setFirstName(appuser.getFirstName());
	user.setLastName(appuser.getLastName());
	user.setPhone(appuser.getPhone());
	user.setAddress(appuser.getAddress());
	user.setCity(appuser.getCity());
	user.setZipCode(appuser.getZipCode());

	castomer.setFirstName(appuser.getFirstName());
	castomer.setLastName(appuser.getLastName());
	castomer.setPhone(appuser.getPhone());
	castomer.setAddress(appuser.getAddress());
	castomer.setCity(appuser.getCity());
	castomer.setZipCode(appuser.getZipCode());

	if(carrier.isPresent()) {
	    	carrier.get().setFirstName(appuser.getFirstName());
	    	carrier.get().setLastName(appuser.getLastName());
	    	carrier.get().setPhone(appuser.getPhone());
	    	carrier.get().setAddress(appuser.getAddress());
	    	carrier.get().setCity(appuser.getCity());
	    	carrier.get().setZipCode(appuser.getZipCode());
	    	carrierRepo.save(carrier.get());
	}
	
	userRepo.save(user);
	customerRepo.save(castomer);
	
    
    }
}
