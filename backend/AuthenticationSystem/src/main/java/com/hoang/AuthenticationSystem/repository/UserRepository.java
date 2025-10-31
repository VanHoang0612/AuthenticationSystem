package com.hoang.AuthenticationSystem.repository;

import com.hoang.AuthenticationSystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    public boolean existsByEmail(String email);
    public boolean existsByUsername(String username);

    public Optional<User> findByEmail(String email);
    public Optional<User> findByUsername(String username);

}
