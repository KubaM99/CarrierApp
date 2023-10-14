package com.example.App.model;

import org.springframework.security.core.GrantedAuthority;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "ROLE")
public class Role implements GrantedAuthority {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "role_id")
	private Long id;
	private String authority;

	public Role(Long id, String authority) {
		super();
		this.id = id;
		this.authority = authority;
	}

	public Role() {
		super();
	}

	public Role(String authority) {
		super();
		this.authority = authority;
	}

	public Long getId() {
		return id;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	@Override
	public String getAuthority() {
		// TODO Auto-generated method stub
		return this.authority;
	}
}
