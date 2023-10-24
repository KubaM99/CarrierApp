package com.example.App.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.hibernate.annotations.GeneratorType;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.ValueGenerationType;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.annotation.Generated;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Data;

@Entity
@Table(name = "CARRIER")
public class Carrier implements UserDetails  {

    	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
    	@Column(name = "user_id")
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
	
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "carrier")//carrier
	private List<Delivery> deliveries;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "role_join_user", joinColumns = { @JoinColumn(name = "user_id") }, inverseJoinColumns = {
			@JoinColumn(name = "role_id") })
	private Set<Role> rols;
	
	


	public Carrier(String firstName, String lastName, String phone, String address, String city, String zipCode,
		String email, String password) {
	    
        	this.firstName = firstName;
        	this.lastName = lastName;
        	this.phone = phone;
        	this.address = address;
        	this.city = city;
        	this.zipCode = zipCode;
        	this.email = email;
        	this.password = password;

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


	@JsonIgnore
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
	    	List<SimpleGrantedAuthority> authorities = new ArrayList<>();
	        for (Role role : rols) {
	            authorities.add(new SimpleGrantedAuthority(role.getAuthority()));
	        }
	        return authorities;
	
	}


	@Override
	public String getUsername() {
	    // TODO Auto-generated method stub
	    return this.email;
	}


	@Override
	public boolean isAccountNonExpired() {
	    // TODO Auto-generated method stub
	    return true;
	}


	@Override
	public boolean isAccountNonLocked() {
	    // TODO Auto-generated method stub
	    return true;
	}


	@Override
	public boolean isCredentialsNonExpired() {
	    // TODO Auto-generated method stub
	    return true;
	}


	@Override
	public boolean isEnabled() {
	    // TODO Auto-generated method stub
	    return true;
	}


	
	
	public Set<Role> getAuthoritis() {
		return rols;
	}
        
	public void setAuthoritis(Set<Role> authoritis) {
		this.rols = authoritis;
	}
        

	
	
	
	
	
}
