package com.example.App.repo;

import static org.assertj.core.api.Assertions.assertThat;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.App.model.Carrier;


@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class CarrierRepoTest {
    
    @Autowired
    private CarrierRepo carrierRepo;

    @Test
    void testFindByIdLong() {
	
	Carrier carrier = new Carrier(
		    "JAN",
		    "jankowalski@abc.pl");
	
	carrierRepo.save(carrier);
	
	
	
	var result = carrierRepo.findById(carrier.getId()).get();
	
	assertThat(result).isNotNull();
    }

    @Test
    void testFindByEmail() {
	
	Carrier carrier = new Carrier(
		    "JAN",
		    "jankowalski@abc.pl");
	
	carrierRepo.save(carrier);
	
	var result = carrierRepo.findByEmail(carrier.getEmail()).get();
	
	assertThat(result).isNotNull();
	assertThat(result.getEmail()).isEqualTo(result.getEmail());
    }

}
