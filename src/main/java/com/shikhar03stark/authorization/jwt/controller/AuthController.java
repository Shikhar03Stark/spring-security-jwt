package com.shikhar03stark.authorization.jwt.controller;

import java.util.Optional;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shikhar03stark.authorization.jwt.dto.AuthLoginRequestDTO;
import com.shikhar03stark.authorization.jwt.dto.AuthRegisterRequestDTO;
import com.shikhar03stark.authorization.jwt.dto.AuthResponseDTO;
import com.shikhar03stark.authorization.jwt.service.AuthService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = "/v1")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;


    @PostMapping(path = "/auth/register")
    public HttpEntity<AuthResponseDTO> registerUser(@RequestBody AuthRegisterRequestDTO authRegisterRequestDTO){

        Optional<AuthResponseDTO> responseOptional = authService.registerUser(authRegisterRequestDTO);

        return responseOptional
            .map(response -> new ResponseEntity<>(response, HttpStatusCode.valueOf(201)))
            .orElse(new ResponseEntity<>(HttpStatusCode.valueOf(403)));
    }

    @PostMapping(path = "/auth/login")
    public HttpEntity<AuthResponseDTO> authenticateUser(@RequestBody AuthLoginRequestDTO authLoginRequestDTO){
        Optional<AuthResponseDTO> resOptional = authService.loginUser(authLoginRequestDTO);

        return resOptional
            .map(response -> new ResponseEntity<>(response, HttpStatusCode.valueOf(200)))
            .orElse(new ResponseEntity<>(HttpStatusCode.valueOf(403)));
    }
}
