package com.quadra.userservice.domain.user.repository;

import com.quadra.userservice.domain.user.entity.User;

import java.util.Optional;

public interface UserRepository {
    Optional<User> findById(long id);
    Optional<User> findByEmail(String email);
}
