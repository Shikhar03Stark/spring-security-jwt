package com.shikhar03stark.authorization.jwt.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthResponseDTO {
    private String authToken;
}
