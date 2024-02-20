package com.passion.foodRecipe.Config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
public class JwtProvider {
    private SecretKey key = Keys.hmacShaKeyFor(JwtConstant.JWT_SECRET.getBytes());
    public String generateToken(Authentication auth){
        String jwt = Jwts.builder()
                .setIssuedAt(new Date())
                .setExpiration(new Date( new Date().getTime()+86400000))
                .claim("userEmail", auth.getName())
                .signWith(key).compact();
        return jwt;
    }

    public String getEmailFromJwtToken(String jwt){
        // 7 because: a token is passed in bearer->jwt
        jwt.trim().substring("Bearer ".length());
        Claims claims =Jwts.parserBuilder()
                          .setSigningKey(key)
                          .build()
                          .parseClaimsJws(jwt)
                          .getBody();
        String userEmail = String.valueOf(claims.get("userEmail"));

        return userEmail;
    }
}
