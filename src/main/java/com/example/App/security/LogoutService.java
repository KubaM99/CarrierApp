package com.example.App.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.example.App.repo.TokenRepo;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class LogoutService implements LogoutHandler {
    
    @Autowired
    private TokenRepo tokenRepo;

    @Override
    public void logout(HttpServletRequest request,
	    HttpServletResponse response,
	    Authentication authentication) {
	
	final String header = request.getHeader("Authorization");
	final String jwtToken;

	if(header==null || !header.startsWith("Bearer ")) {
	    return;
	}
	
	jwtToken = header.substring(7);
	
	var token = tokenRepo.findByToken(jwtToken);
	
	if(token.isPresent()) {
	    token.get().setRevoked(true);
	    token.get().setExpired(true);
	    
	    tokenRepo.save(token.get());
	}
	

    }

}
