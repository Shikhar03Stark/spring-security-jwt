package com.shikhar03stark.authorization.jwt.mapper;

import org.mapstruct.Mapper;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.shikhar03stark.authorization.jwt.dto.AuthRegisterRequestDTO;
import com.shikhar03stark.authorization.jwt.entity.UserEntity;
import com.shikhar03stark.authorization.jwt.entity.UserRole;

@Mapper(componentModel = "spring")
public interface AuthUserMapper {

    default UserEntity toUserEntity(AuthRegisterRequestDTO registerRequestDTO, PasswordEncoder passwordEncoder){
        return UserEntity
        .builder()
        .email(registerRequestDTO.getEmail())
        .firstname(registerRequestDTO.getFirstname())
        .lastname(registerRequestDTO.getLastname())
        .userRole(UserRole.DEFAULT)
        .password(passwordEncoder.encode(registerRequestDTO.getPassword()))
        .build();

    }
}
