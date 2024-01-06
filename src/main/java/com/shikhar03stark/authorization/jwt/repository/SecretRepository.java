package com.shikhar03stark.authorization.jwt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shikhar03stark.authorization.jwt.entity.SecretEntity;


@Repository
public interface SecretRepository extends JpaRepository<SecretEntity, String> {

}
