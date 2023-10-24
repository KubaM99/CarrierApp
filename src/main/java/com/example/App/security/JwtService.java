package com.example.App.security;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.example.App.model.Customer;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;


@Component
public class JwtService {
    
    private static final String SECURITY_KEY = "404E635266556A586E3272357538782F413F4428472B4B6250645367566B5970";

    
    
    
    public String generateToken(UserDetails userDetails) {
	return generateToken(new HashMap<>(),userDetails);
    }
    
    public String generateToken(Map<String,Object> claims, UserDetails userDetails) {
	
	return Jwts.builder()
		.setClaims(claims)
		.setSubject(userDetails.getUsername())
		.claim("rols", userDetails.getAuthorities())
		.setIssuedAt(new Date(System.currentTimeMillis()))
		.setExpiration(new Date(System.currentTimeMillis()+1000*60*24))
		.signWith(signKey(),SignatureAlgorithm.HS256)
		.compact();
	
    }
    
    public String extractEmail(String token) {
	return extractClaim(token, Claims::getSubject);
    }
    
    public <T> T extractClaim(String token, Function<Claims, T> claimsResult ) {
	
	final Claims claim = extractAllClaims(token);
	return claimsResult.apply(claim);
	
    }

    private Claims extractAllClaims(String toke) {
	
	return Jwts.parserBuilder()
		.setSigningKey(signKey())
		.build()
		.parseClaimsJws(toke)
		.getBody();
    }

    private Key signKey() {
	byte[] keyByte = Decoders.BASE64.decode(SECURITY_KEY);
	return Keys.hmacShaKeyFor(keyByte);
    }
    
    public boolean checkToken(String token, UserDetails userDetails) {
	String emailToken = extractEmail(token);
	
	return (emailToken.equals(userDetails.getUsername())) && !isExpire(token);
	
    }

    private boolean isExpire(String token) {
	return extractExpire(token).before(new Date());
    }

    private Date extractExpire(String token) {
	return extractClaim(token, Claims::getExpiration );
    }
    
   
}
