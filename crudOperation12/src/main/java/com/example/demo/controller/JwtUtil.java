package com.example.demo.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
@Service
public class JwtUtil {
	
	private String SECRET_KEY ="secret";
	// steps 4
	public String extractUsername(String token) {
		return extractClaim(token,Claims::getSubject);
		
	}
	// steps 5
	public Date extractExpiration(String token) {
		return extractClaim(token,Claims::getExpiration);
	}
	// steps 3
	public <T> T extractClaim(String token,Function<Claims,T> claimsResolver) {
		final Claims claims = extractAllClaims(token);
		return claimsResolver.apply(claims);
	}
	private Claims extractAllClaims(String token) {
		return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
	}
	// steps 6
	private Boolean isTokenExpired(String token)
	{
		return extractExpiration(token).before(new Date());
	}
	
	// steps 1
	public String generateToken(UserDetails userDetails) {
		Map<String,Object> claims=new HashMap<>();
		return createToken(claims,userDetails.getUsername());
		
	}
	// steps 2
	public String createToken(Map<String,Object> claims,String subject)
	{
		return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis())).setExpiration(new Date(System.currentTimeMillis()+1000*60*60*10))
				.signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
	}
	// steps 7
	public Boolean validateToken(String token,UserDetails userDetails)
	{
		final String username =extractUsername(token);
		return (username.equals(userDetails.getUsername())&& !isTokenExpired(token));
	}
	
	

}
