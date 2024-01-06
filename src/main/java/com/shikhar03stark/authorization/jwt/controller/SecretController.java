package com.shikhar03stark.authorization.jwt.controller;

import java.util.Optional;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shikhar03stark.authorization.jwt.dto.SecretRequestDTO;
import com.shikhar03stark.authorization.jwt.dto.SecretResponseDTO;
import com.shikhar03stark.authorization.jwt.service.SecretService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
public class SecretController {

    private final SecretService secretService;

    @GetMapping("/secret/{id}")
    public HttpEntity<SecretResponseDTO> getSecret(@PathVariable(name = "id", required = true) String id){
        Optional<SecretResponseDTO> resOptional = secretService.getSecretById(id);
        if(resOptional.isPresent()){
            return new ResponseEntity<SecretResponseDTO>(resOptional.get(), HttpStatusCode.valueOf(200));
        } else {
            return new ResponseEntity<>(HttpStatusCode.valueOf(404));
        }
    }

    @PostMapping("/secret")
    public HttpEntity<SecretResponseDTO> createSecret(@RequestBody SecretRequestDTO secretRequestDTO) {
        SecretResponseDTO responseDTO = secretService.createSecret(secretRequestDTO);
        return new ResponseEntity<>(responseDTO, HttpStatusCode.valueOf(201));
    }

}
