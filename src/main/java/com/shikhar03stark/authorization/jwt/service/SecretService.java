package com.shikhar03stark.authorization.jwt.service;

import java.util.Optional;

import com.shikhar03stark.authorization.jwt.dto.SecretRequestDTO;
import com.shikhar03stark.authorization.jwt.dto.SecretResponseDTO;

public interface SecretService {
    Optional<SecretResponseDTO> getSecretById(String Id);

    SecretResponseDTO createSecret(SecretRequestDTO secretRequestDTO);
}
