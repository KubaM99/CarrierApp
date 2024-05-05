package com.example.App.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.App.model.Card;
import com.example.App.model.Carrier;
import com.example.App.security.AuthentService;

import jakarta.transaction.Transactional;


public interface CardRepo extends JpaRepository<Card, Long> {
    

   
    Optional<List<Card>> findByCustomerId(Long customerId);
    
    @Modifying
    @Query("DELETE FROM Card c WHERE c.customer.id = ?1")
    void deleteByCustomerId(Long id);
    
   
    @Query(value = "SELECT * FROM card WHERE customer_id = ?1 AND sku = ?2 Limit 1",nativeQuery = true)
    Optional<Card> findOneCardItem(Long id, Long sku);
    
    @Modifying
    @Query("DELETE FROM Card c WHERE c.id = ?1")
    void deleteCartItem(Long id);
    
    //@Modifying
    //@Query(value = "SELECT * FROM card WHERE customer_id = ?1",nativeQuery = true)
    //Optional<List<Card>> findAllCardItemsForCustomer(Long id);
}

