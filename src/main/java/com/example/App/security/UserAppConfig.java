package com.example.App.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.App.repo.CarrierRepo;
import com.example.App.repo.CustomerRepo;
import com.example.App.repo.UserRepo;

import lombok.RequiredArgsConstructor;

@Configuration
public class UserAppConfig  {
    
    @Autowired
    private UserRepo userRepo;
    
    
    @Bean
    public UserDetailsService userDetailsServiceCustomer() {
      return username -> userRepo.findByEmail(username)
          .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
    
    @Bean
    public AuthenticationProvider authenticationProviderUser() {
      DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
      authProvider.setUserDetailsService(userDetailsServiceCustomer());
      authProvider.setPasswordEncoder(passwordEncoder());
      return authProvider;
    }
    
    
    @Bean
    public BCryptPasswordEncoder  passwordEncoder() {
	return new BCryptPasswordEncoder();
   }
    
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
	    throws Exception {
	return authenticationConfiguration.getAuthenticationManager();
    }

   
}
