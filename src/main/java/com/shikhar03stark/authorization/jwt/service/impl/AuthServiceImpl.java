package com.shikhar03stark.authorization.jwt.service.impl;

import java.util.Objects;
import java.util.Optional;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.shikhar03stark.authorization.jwt.dto.AuthLoginRequestDTO;
import com.shikhar03stark.authorization.jwt.dto.AuthRegisterRequestDTO;
import com.shikhar03stark.authorization.jwt.dto.AuthResponseDTO;
import com.shikhar03stark.authorization.jwt.entity.UserEntity;
import com.shikhar03stark.authorization.jwt.mapper.AuthUserMapper;
import com.shikhar03stark.authorization.jwt.repository.UserRepository;
import com.shikhar03stark.authorization.jwt.service.AuthService;
import com.shikhar03stark.authorization.jwt.service.JwtService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    
    private final UserRepository userRepository;
    private final AuthUserMapper authUserMapper;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @Override
    public Optional<AuthResponseDTO> registerUser(AuthRegisterRequestDTO authRegisterRequestDTO) {
        final String email = authRegisterRequestDTO.getEmail();

        final UserEntity entity = userRepository.findByEmail(email).orElse(null);
        if(Objects.nonNull(entity)){
            return Optional.empty();
        }

        final UserEntity newUser = authUserMapper.toUserEntity(authRegisterRequestDTO, passwordEncoder);
        final UserEntity savedUserEntity = userRepository.save(newUser);
        final String jwtToken = jwtService.generateToken(savedUserEntity);

        final AuthResponseDTO responseDTO = AuthResponseDTO.builder().authToken(jwtToken).build();

        return Optional.of(responseDTO);
    }

    @Override
    public Optional<AuthResponseDTO> loginUser(AuthLoginRequestDTO authLoginRequestDTO) {
        final Optional<UserEntity> optionalUserEntity = userRepository.findByEmail(authLoginRequestDTO.getEmail());
        if (optionalUserEntity.isEmpty()){
            return Optional.empty();
        }
        Authentication authentication = new UsernamePasswordAuthenticationToken(authLoginRequestDTO.getEmail(), authLoginRequestDTO.getPassword());
        if (authenticationManager.authenticate(authentication).isAuthenticated()) {
            final UserEntity userEntity = optionalUserEntity.get();
            final String jwtToken = jwtService.generateToken(userEntity);
            final AuthResponseDTO responseDTO = AuthResponseDTO.builder().authToken(jwtToken).build();
            return Optional.of(responseDTO);
        } else {
            return Optional.empty();
        }
    }


}
