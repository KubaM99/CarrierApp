package com.example.App.model;

import org.hibernate.annotations.ManyToAny;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
public class Token {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String token;
    
    @Enumerated(EnumType.STRING)
    private TokenType tokenType;
    
    private boolean expired;
    
    private boolean revoked;
    
    

    @ManyToOne
    private AppUser user;
    
    
    public Token() {
	super();
    }

    public Token(Long id, String token, TokenType tokenType, boolean expired, boolean revoked, AppUser user) {
	super();
	this.id = id;
	this.token = token;
	this.tokenType = tokenType;
	this.expired = expired;
	this.revoked = revoked;
	this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public TokenType getTokenType() {
        return tokenType;
    }

    public void setTokenType(TokenType tokenType) {
        this.tokenType = tokenType;
    }

    public boolean isExpired() {
        return expired;
    }

    public void setExpired(boolean expired) {
        this.expired = expired;
    }

    public boolean isRevoked() {
        return revoked;
    }

    public void setRevoked(boolean revoked) {
        this.revoked = revoked;
    }

    public AppUser getAppUser() {
        return user;
    }

    public void setAppUser(AppUser appUser) {
        this.user = appUser;
    }
    
    
    
    

}
