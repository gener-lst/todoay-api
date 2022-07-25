package com.todoay.api.domain.auth.repository;

import com.todoay.api.domain.auth.entity.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken,Long> {

    Optional<RefreshToken> findByToken(String token);
    Optional<RefreshToken> findBySubjectEmail(String email);

    void deleteBySubjectEmail(String email);
}
