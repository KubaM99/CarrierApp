package com.example.App.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.App.model.Role;
import java.util.Optional;


public interface RoleRepo extends JpaRepository<Role, Long> {
    
    Optional<Role> findByAuthority(String authority);

   

}
