package com.shikhar03stark.authorization.jwt.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.shikhar03stark.authorization.jwt.config.JwtConfiguration;
import com.shikhar03stark.authorization.jwt.service.JwtService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JwtServiceImpl implements JwtService{

    private final JwtParser jwtParser;
    private final JwtConfiguration jwtConfiguration;

    @Override
    public String getEmail(String token) {
        return extractFromClaims(token, Claims::getSubject);
    }

    @Override
    public Claims extractAllClaims(String token) {
        return jwtParser.parseSignedClaims(token).getPayload();
    }

    @Override
    public <T> T extractFromClaims(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    @Override
    public boolean isTokenExpired(String token) {
        Date expiration = extractFromClaims(token, Claims::getExpiration);
        return expiration.before(new Date(System.currentTimeMillis()));
    }

    @Override
    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String email = getEmail(token);
        return email.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    @Override
    public String generateToken(UserDetails userDetails, Map<String, Object> extraClaims) {
        return Jwts.builder()
                .subject(userDetails.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + jwtConfiguration.getExpireDurationMillis()))
                .claims(extraClaims)
                .signWith(jwtConfiguration.getSecretKey(), Jwts.SIG.HS256)
                .compact();
    }

    @Override
    public String generateToken(UserDetails userDetails) {
        return generateToken(userDetails, new HashMap<>());
    }

}
