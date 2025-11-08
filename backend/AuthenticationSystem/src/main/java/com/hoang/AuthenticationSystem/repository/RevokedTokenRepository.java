package com.hoang.AuthenticationSystem.repository;

import com.hoang.AuthenticationSystem.model.RevokedToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface RevokedTokenRepository extends JpaRepository<RevokedToken, Long> {

    boolean existsByJti(UUID jti);

    Optional<RevokedToken> findByJti(UUID jti);
}
