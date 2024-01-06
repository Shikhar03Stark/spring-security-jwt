package com.shikhar03stark.authorization.jwt.service.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.shikhar03stark.authorization.jwt.dto.SecretRequestDTO;
import com.shikhar03stark.authorization.jwt.dto.SecretResponseDTO;
import com.shikhar03stark.authorization.jwt.entity.SecretEntity;
import com.shikhar03stark.authorization.jwt.mapper.SecretMapper;
import com.shikhar03stark.authorization.jwt.repository.SecretRepository;
import com.shikhar03stark.authorization.jwt.service.SecretService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SecretServiceImpl implements SecretService{

    private final SecretRepository secretRepository;
    private final SecretMapper secretMapper;

    @Override
    public Optional<SecretResponseDTO> getSecretById(String Id) {
        return secretRepository
        .findById(Id)
        .map(secretMapper::toSecretResponseDTO);
    }

    @Override
    public SecretResponseDTO createSecret(SecretRequestDTO secretRequestDTO) {
        final SecretEntity secretEntity = secretMapper.toSecretEntity(secretRequestDTO);
        final SecretEntity savedSecretEntity = secretRepository.save(secretEntity);
        return secretMapper.toSecretResponseDTO(savedSecretEntity);
    }

}
