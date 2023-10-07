package com.example.App.model;

import java.util.List;

import org.hibernate.annotations.GeneratorType;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.ValueGenerationType;

import jakarta.annotation.Generated;
import jakarta.persistence.CascadeType;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "CARRIER")
public class Carrier {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "carrierIdGen" )
	@SequenceGenerator(name = "carrierIdGen", initialValue = 1000)
	private Long id;
	
	
	//@GenericGenerator(name = "carierIdGenerateSeq",strategy = "enhanced-sequence",
	//		parameters = {
	//				@Parameter(name = "sequence_name", value = "JPWH_SEQUENCE\""),
	//				@Parameter(name = "initial_value", value = "1000")
	//		})
	
	//private Long carrierId;
	private String firstName;
	private String lastName;
	private String phone;	
	private String address;
	private String city;
	private String zipCode;
	private String email;
	private String password;
	
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "carrier")
	private List<Delivery> deliveries;
	
	

	


	public Carrier(Long carrierId, String firstName, String lastName, String phone, String address, String city,
			String zipCode, String email, String password, List<Delivery> deliveries) {
		super();
		//this.carrierId = carrierId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phone = phone;
		this.address = address;
		this.city = city;
		this.zipCode = zipCode;
		this.email = email;
		this.password = password;
		this.deliveries = deliveries;
	}


	public Carrier() {
		super();
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}

    //
	//public Long getCarrierId() {
	//	return carrierId;
	//}
    //
    //
	//public void setCarrierId(Long carrierId) {
	//	this.carrierId = carrierId;
	//}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}


	public String getZipCode() {
		return zipCode;
	}


	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public List<Delivery> getDeliveries() {
		return deliveries;
	}


	public void setDeliveries(List<Delivery> deliveries) {
		this.deliveries = deliveries;
	}
	
	
	
	
	
}
