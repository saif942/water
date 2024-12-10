package com.water.login.clockINclockOUT.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtUtils {
    private static final String SECRET_KEY = "your-256-bit-secret-your-256-bit-secret";
    private static final long EXPIRATION_TIME = 1000 * 60 * 10; //10 min

    private final SecretKey key;

    public JwtUtils() {
        this.key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }

    public String getUserName(String token){
        return extractToken(token).getSubject();
    }

    private  Claims extractToken(String token){
        return Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload();
    }

    public boolean isTokenExpired(String token){
        try{
            return extractToken(token).getExpiration().before(new Date());
        }catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    public String generateToken(UserDetails userDetails){
        Map<String, String> claims = new HashMap<>();
        claims.put("userName",userDetails.getUsername());
        claims.put("password",userDetails.getPassword());
        return createToken(claims,userDetails);
    }

    private String createToken(Map<String, String> claims, UserDetails userDetails) {
        return Jwts.builder().subject(userDetails.getUsername())
                .claims(claims)
                .header().empty().add("typ","jwt")
                .and()
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis()+EXPIRATION_TIME))
                .signWith(key)
                .compact();
    }
}

