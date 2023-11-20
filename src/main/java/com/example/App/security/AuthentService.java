package com.example.App.security;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.App.dto.CustomerPostDTO;
import com.example.App.dto.LoginDTO;
import com.example.App.exceptation.CustomerExsistExceptation;
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
    private PasswordEncoder encoder;

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

    public String loginUser(LoginDTO loginDTO) {

	var user = userRepo.findByEmail(loginDTO.getEmail())
		.orElseThrow(() -> new UserNotFoundExceptaion("User doesnt exsist"));

	authenticationManager
		.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword()));

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
	//var token = Token.builder()
	//	.appUser(appUser)
	//	.token(jwtToken)
	//	.tokenType(TokenType.BEARER)
	//	.revoked(false)
	//	.expired(false)
	//	.build();
	//
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

	// if(tokens.isEmpty()) return;

	System.out.println(tokens);

	tokens.forEach(t -> {
	    t.setRevoked(true);
	    t.setExpired(true);
	});

	tokenRepo.saveAll(tokens);
    }
    

}
