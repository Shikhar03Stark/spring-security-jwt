package com.shikhar03stark.authorization.jwt.mapper;

import java.time.Instant;
import java.util.UUID;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import com.shikhar03stark.authorization.jwt.dto.SecretRequestDTO;
import com.shikhar03stark.authorization.jwt.dto.SecretResponseDTO;
import com.shikhar03stark.authorization.jwt.entity.SecretEntity;

@Mapper(componentModel = "spring")
public interface SecretMapper {

    @Mapping(target = "Id", source = "id")
    SecretResponseDTO toSecretResponseDTO(SecretEntity secretEntity);

    default SecretEntity toSecretEntity(SecretRequestDTO secretRequestDTO) {
        final SecretEntity secretEntity = new SecretEntity();
        final Instant nowInstant = Instant.now();
        secretEntity.setPayload(secretRequestDTO.getPayload());
        secretEntity.setCreatedAt(nowInstant);
        secretEntity.setId(UUID.randomUUID().toString());
        secretEntity.setExpireAt(secretRequestDTO.getDuration().toInstant(nowInstant));
        return secretEntity;
    }
}
