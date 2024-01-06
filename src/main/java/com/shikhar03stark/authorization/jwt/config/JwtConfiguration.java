package com.shikhar03stark.authorization.jwt.config;

import java.security.Key;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetailsService;

import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Configuration
@RequiredArgsConstructor
public class JwtConfiguration {

    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.expiration.second}")
    private long expireDurationSeconds;

    public SecretKey getSecretKey(){
        final byte[] secretKeyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(secretKeyBytes);
    }

    public long getExpireDurationMillis(){
        return expireDurationSeconds*1000;
    }

    @Bean
    public JwtParser getJwtParser(){
        return Jwts
                .parser()
                .verifyWith(getSecretKey())
                .build();
    }

}
