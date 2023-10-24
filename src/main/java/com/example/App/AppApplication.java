package com.example.App;

import java.util.HashSet;
import java.util.Set;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.App.model.Customer;
import com.example.App.model.Role;
import com.example.App.repo.CustomerRepo;
import com.example.App.repo.RoleRepo;

@SpringBootApplication
public class AppApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppApplication.class, args);
	}
	
	//@Bean
	//CommandLineRunner runAdmin(CustomerRepo customerRepo, PasswordEncoder encoder, RoleRepo roleRepo){
	//    return args->{
	//	
	//	if(roleRepo.findByAuthority("ADMIN").isPresent()) return;
	//	
	//	Role adminR = roleRepo.save(new Role("ADMIN"));
	//	roleRepo.save(new Role("USER"));
	//	roleRepo.save(new Role("CARRIER"));
	//	
	//	Set<Role> roles = new HashSet<>();
	//	roles.add(adminR);
	//	
	//	Customer admin  = new Customer(1L,"admin@admin",encoder.encode("password"),roles);
	//	
	//	
	//	customerRepo.save(admin);
	//    };
	//    
	//}

}
