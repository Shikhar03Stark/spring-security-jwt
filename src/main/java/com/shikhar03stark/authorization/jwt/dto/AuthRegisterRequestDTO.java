package com.shikhar03stark.authorization.jwt.dto;

import lombok.Data;

@Data
public class AuthRegisterRequestDTO {
    private String email;
    private String password;
    private String firstname;
    private String lastname;
}
