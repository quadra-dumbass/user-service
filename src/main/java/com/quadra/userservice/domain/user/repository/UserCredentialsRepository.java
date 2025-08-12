package com.quadra.userservice.domain.user.repository;

import com.quadra.userservice.domain.user.entity.UserCredentials;

import java.util.Optional;

public interface UserCredentialsRepository {
    Optional<UserCredentials> findByLoginId(String loginId);
}
