package com.example.App.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.App.model.Card;
import com.example.App.model.Carrier;


public interface CardRepo extends JpaRepository<Card, Long> {

   
    Optional<List<Card>> findByCustomerId(Long customerId);
    
    @Modifying
    @Query("DELETE FROM Card c WHERE c.customer.id = ?1")
    void deleteByCustomerId(Long id);
}
