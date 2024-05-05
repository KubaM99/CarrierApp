package com.example.App.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "CUSTOMER")
public class Customer  {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name =  "id")
	private Long id;

	private String firstName;
	private String lastName;
	private String phone;
	private String address;
	private String city;
	private String zipCode;
	@Column(nullable = false,unique = true)
	private String email;
	private String password;


	@OneToOne(cascade = CascadeType.ALL)
	    @JoinColumn(name = "user_id", referencedColumnName = "id")
	    private AppUser appuser;


	//@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
	//private List<Delivery> deliveries;

	
	public Customer() {}

	public Customer(String firstName, String lastName, String phone, String address, String city, String zipCode,
			String email, String password,AppUser appuser) {

		this.firstName = firstName;
		this.lastName = lastName;
		this.phone = phone;
		this.address = address;
		this.city = city;
		this.zipCode = zipCode;
		this.email = email;
		this.password = password;
		this.appuser = appuser;
	}

	public Customer(String firstName, String email) {
		super();
		this.firstName = firstName;
		this.email = email;
	}
	public Customer(Long id,String firstName, String email) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getPhone() {
		return phone;
	}

	public String getAddress() {
		return address;
	}

	public String getCity() {
		return city;
	}

	public String getZipCode() {
		return zipCode;
	}

	public String getEmail() {
		return email;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPassword() {
	    return password;
	}

	public void setPassword(String password) {
	    this.password = password;
	}

	public AppUser getAppuser() {
	    return appuser;
	}

	public void setAppuser(AppUser appuser) {
	    this.appuser = appuser;
	}

	//public List<Delivery> getDeliveries() {
	//    return deliveries;
	//}
        //
	//public void setDeliveries(List<Delivery> deliveries) {
	//    this.deliveries = deliveries;
	//}
        //
	
	
	
	

}
