package com.example.App.repo;

import java.lang.foreign.Linker.Option;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.App.model.Token;

public interface TokenRepo extends JpaRepository<Token, Long> {
    
    

    @Query("SELECT t FROM Token t WHERE t.user.id = ?1 AND (t.expired = false OR t.revoked = false)")
    List<Token> findAllValidTokensByUser(Long id); 
    
    
    Optional<Token> findByToken(String token);


  
}