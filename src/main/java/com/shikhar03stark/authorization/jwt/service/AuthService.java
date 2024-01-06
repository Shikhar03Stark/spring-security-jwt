package com.shikhar03stark.authorization.jwt.service;

import java.util.Optional;

import com.shikhar03stark.authorization.jwt.dto.AuthLoginRequestDTO;
import com.shikhar03stark.authorization.jwt.dto.AuthRegisterRequestDTO;
import com.shikhar03stark.authorization.jwt.dto.AuthResponseDTO;

public interface AuthService {

    Optional<AuthResponseDTO> registerUser(AuthRegisterRequestDTO authRegisterRequestDTO);
    Optional<AuthResponseDTO> loginUser(AuthLoginRequestDTO authLoginRequestDTO);
}
