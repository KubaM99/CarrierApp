package com.example.App.repo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.App.model.Product;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class ProductRepoTest {

    @Autowired
    private ProductRepo productRepo;
    
    @Test
    void testFindBySku() {
	
	Product product = new Product(
		"XXX",
		111L,
		11);
	
	
	productRepo.save(product);
	
	var s = productRepo.findBySku(product.getSku()).get();
	
	assertThat(s).isNotNull();
	assertThat(s.getId()).isGreaterThan(0);
	assertThat(s.getSku()).isEqualTo(product.getSku());
	
    }

}
