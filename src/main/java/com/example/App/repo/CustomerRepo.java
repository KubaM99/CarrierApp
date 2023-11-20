package com.example.App.repo;


import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.example.App.model.Customer;


public interface CustomerRepo extends JpaRepository<Customer, Long> {
	
	public Optional<Customer> getCustomerByEmailAndFirstName(String email,String firstname);
	
	//@Modifying
	//@Query("DELETE Customer c WHERE c.email =?1")
	//void deleteByEmail(String email);
	
	@Modifying
	@Query("SELECT count(c) = 1 from Customer c where c.email = ?1")
	public boolean findExistByEmial(String email);
	
	public Optional<Boolean> existsByEmail(String eemail);
	
	public Optional<Customer> findByEmail(String email);
	
	
}
