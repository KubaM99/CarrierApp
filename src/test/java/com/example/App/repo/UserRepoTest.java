package com.example.App.repo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.App.model.AppUser;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class UserRepoTest {

    @Autowired
    private UserRepo userRepo;
   
    
    @Test
    void testFindByEmail() {
	
	  AppUser user = new AppUser(
		    "JAN",
		    "KOWALSKI",
		    "1234567898",
		    "KWIATOWA 1",
		    "WARSZAWA",
		    "00=001",
		    "jankowalski@abc.pl",
		    "12345"
		    );
	  
	  userRepo.save(user);
	  
	  var u = userRepo.findByEmail(user.getEmail()).get();
	  
	  assertThat(u).isNotNull();
	  assertThat(u.getEmail()).isEqualTo(user.getEmail());
	 
    }

}
