package com.example.App.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.App.model.AppUser;
import com.example.App.model.Carrier;

public interface UserRepo extends JpaRepository<AppUser, Long> {
    
    Optional<AppUser> findByEmail(String email);
	
    Boolean existsByEmail(String eemail);

}
