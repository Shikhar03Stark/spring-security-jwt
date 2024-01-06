package com.shikhar03stark.authorization.jwt.dto;

import lombok.Data;

@Data
public class AuthLoginRequestDTO {
    private String email;
    private String password;
}
