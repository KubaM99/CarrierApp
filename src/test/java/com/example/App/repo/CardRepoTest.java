package com.example.App.repo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.App.model.Card;
import com.example.App.model.Customer;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class CardRepoTest {

    @Autowired
    private CustomerRepo customerRepo;
    @Autowired
    private CardRepo cardRepo;

    @Test
    void testFindByCustomerId() {

	Customer customer = new Customer("JAN", "jankowalski@abc.pl");

	customerRepo.save(customer);

	Card card = new Card(customer, 12345L, "XXX", 10);

	cardRepo.save(card);

	var result = cardRepo.findByCustomerId(customer.getId()).get();

	assertThat(result).isNotNull();

    }

    @Test
    void testDeleteByCustomerId() {
	
	Customer customer = new Customer(
		    "JAN",
		    "jankowalski@abc.pl");
	
	customerRepo.save(customer);
	
	Card card1 = new Card(customer, 12345L, "XXX", 10);
	Card card2= new Card(customer, 12345L, "XXX", 10);
	Card card3 = new Card(customer, 12346L, "ABC", 10);
	
	cardRepo.save(card1);
	cardRepo.save(card2);
	cardRepo.save(card3);
	
	cardRepo.deleteByCustomerId(customer.getId());
	
	var result = cardRepo.findByCustomerId(customer.getId()).get();
	
	
	assertThat(result.isEmpty()).isTrue();
    }

    @Test
    void testFindOneCardItem() {
	
	Customer customer = new Customer(
		    "JAN",
		    "jankowalski@abc.pl");
	
	customerRepo.save(customer);
	
	Card card1 = new Card(customer, 12345L, "XXX", 10);
	Card card2= new Card(customer, 12345L, "XXX", 10);
	Card card3 = new Card(customer, 12346L, "ABC", 10);
	
	cardRepo.save(card1);
	cardRepo.save(card2);
	cardRepo.save(card3);
	
	var result = cardRepo.findOneCardItem(customer.getId(),12345L).get();
	
	assertThat(result).isNotNull();
	assertThat(result.getSku()).isEqualTo(12345L);
	
    }

    @Test
    void testDeleteCartItem() {
	Customer customer = new Customer(
		    "JAN",
		    "jankowalski@abc.pl");
	
	customerRepo.save(customer);
	
	Card card1 = new Card(customer, 12345L, "XXX", 10);
	Card card2= new Card(customer, 12345L, "XXX", 10);
	Card card3 = new Card(customer, 12346L, "ABC", 10);
	
	cardRepo.save(card1);
	cardRepo.save(card2);
	cardRepo.save(card3);
	
	var v = cardRepo.findOneCardItem(customer.getId(),12345L).get();
	
	cardRepo.deleteCartItem(v.getId());
	
	var result = cardRepo.findByCustomerId(customer.getId()).get();
	
	assertThat(result).isNotNull();
	assertThat(result.size()).isEqualTo(2);
    }

}
