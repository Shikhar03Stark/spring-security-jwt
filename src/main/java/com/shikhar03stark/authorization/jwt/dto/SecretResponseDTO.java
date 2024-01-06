package com.shikhar03stark.authorization.jwt.dto;

import java.time.Instant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class SecretResponseDTO {
    private final String Id;
    private final Instant createdAt;
    private final Instant expireAt;
    private final String payload;
}
