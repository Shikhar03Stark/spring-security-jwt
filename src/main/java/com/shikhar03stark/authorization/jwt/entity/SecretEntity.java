package com.shikhar03stark.authorization.jwt.entity;

import java.time.Instant;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SecretEntity{

    @Id
    private String id;

    @Column(name = "expire_at")
    private Instant expireAt;

    @Column(name = "created_at")
    private Instant createdAt;

    private String payload;
}
