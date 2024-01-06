package com.shikhar03stark.authorization.jwt.service;

import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;

import io.jsonwebtoken.Claims;

public interface JwtService {
    String getEmail(String token);
    Claims extractAllClaims(String token);
    <T> T extractFromClaims(String token, Function<Claims, T> claimsResolver);
    boolean isTokenExpired(String token);
    boolean isTokenValid(String token, UserDetails userDetails);

    String generateToken(UserDetails userDetails, Map<String, Object> extraClaims);
    String generateToken(UserDetails userDetails);

}
