package com.quadra.userservice.domain.user.repository;

import com.quadra.userservice.domain.user.entity.User;

import java.util.Optional;

public interface UserRepository {
    User save(User user);
    Optional<User> findById(Long id);
    Optional<User> findByEmail(String email);
}
