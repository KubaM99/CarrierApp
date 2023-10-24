package com.example.App.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import ch.qos.logback.core.filter.Filter;



@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private JwtAuthConfigurate JwtFilter;
    @Autowired
    private AuthenticationProvider authenticationProvider;

    @Bean
    SecurityFilterChain chain(HttpSecurity http) throws Exception {
	
	
	http.csrf(csrf->csrf.disable())
		
		.authorizeHttpRequests(auth -> { 
		    		auth.requestMatchers("/configuration/**").permitAll();
		    		auth.requestMatchers("/authority/**").permitAll();
		    		auth.anyRequest().authenticated();
	        
			})
		.sessionManagement(session -> 
			session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
		.authenticationProvider(authenticationProvider)
		.addFilterBefore(JwtFilter, UsernamePasswordAuthenticationFilter.class);
		
	
	
	return http.build();
    }
}
