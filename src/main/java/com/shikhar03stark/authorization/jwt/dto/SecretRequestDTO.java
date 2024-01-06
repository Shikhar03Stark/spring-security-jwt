package com.shikhar03stark.authorization.jwt.dto;

import com.shikhar03stark.authorization.jwt.entity.SecretDuration;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class SecretRequestDTO {

    private final SecretDuration duration;
    private final String payload;
}
