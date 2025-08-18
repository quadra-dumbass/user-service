package com.quadra.userservice.domain.user.repository;

import com.quadra.userservice.domain.user.entity.UserOAuthConn;
import com.quadra.userservice.domain.user.enums.OAuth2Provider;

import java.util.Optional;

public interface UserOAuthConnRepository {
    UserOAuthConn save(UserOAuthConn userOAuthConn);
    Optional<UserOAuthConn> findByProviderAndExternalId(String provider, String externalId);
}
