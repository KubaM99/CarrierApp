package com.example.App.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;


@Component
public class JwtAuthConfigurate extends OncePerRequestFilter{
    
    @Autowired
    private JwtService jwtService;
    
    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(
	    @NotNull HttpServletRequest request,
	    @NotNull HttpServletResponse response,
	    @NotNull FilterChain filterChain)
	    throws ServletException, IOException {
	
	final String header = request.getHeader("Authorization");
	final String jwtToken;
	final String email;
	
	if(header==null || !header.startsWith("Bearer ")) {
	    filterChain.doFilter(request, response);
	    return;
	}
	
	jwtToken = header.substring(7);
	email = jwtService.extractEmail(jwtToken);
	
	if(email != null && SecurityContextHolder.getContext().getAuthentication()==null) {
	   UserDetails userDetails = this.userDetailsService.loadUserByUsername(email);
	   
	   if(jwtService.checkToken(jwtToken, userDetails)) {
	       
	       
	       UsernamePasswordAuthenticationToken authoToken =
		    new UsernamePasswordAuthenticationToken(
			    userDetails,
			    null,
			    userDetails.getAuthorities());   
	       authoToken.setDetails(
		       new WebAuthenticationDetailsSource().buildDetails(request));
	       SecurityContextHolder.getContext().setAuthentication(authoToken);
	   }
	}
	filterChain.doFilter(request, response);
    }
    
    
    
    
    
    
    
    

}
