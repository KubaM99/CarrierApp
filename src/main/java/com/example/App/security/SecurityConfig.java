package com.example.App.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;



@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private JwtAuthConfigurate JwtFilter;
    @Autowired
    private AuthenticationProvider authenticationProvider;
    @Autowired
    private LogoutHandler logoutHandler;

    @Bean
    SecurityFilterChain chain(HttpSecurity http) throws Exception {
	
	
	http.csrf(csrf->csrf.disable())
		.authorizeHttpRequests(auth -> { 
		    		auth.requestMatchers("/authority/**").permitAll();
		    		auth.requestMatchers("/product/**").permitAll();
		    		auth.requestMatchers("/carrier/**").hasAuthority("CARRIER");
		    		auth.anyRequest().authenticated();
	        
			})
		.sessionManagement(session -> 
			session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
		.authenticationProvider(authenticationProvider)
		.addFilterBefore(JwtFilter, UsernamePasswordAuthenticationFilter.class)
		.logout(logout ->
				logout.logoutUrl("/authority/logout")
					.addLogoutHandler(logoutHandler)
					.logoutSuccessHandler(
						(request, response, authentication) ->
							SecurityContextHolder.clearContext())
			
			);
		
	
		
                
		
	
	
	return http.build();
    }
}
