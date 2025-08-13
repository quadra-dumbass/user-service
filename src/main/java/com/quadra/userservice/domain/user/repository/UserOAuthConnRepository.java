package com.quadra.userservice.domain.user.repository;

import com.quadra.userservice.application.security.oauth.enums.OAuth2Provider;
import com.quadra.userservice.domain.user.entity.UserOAuthConn;

import java.util.Optional;

public interface UserOAuthConnRepository {
    Optional<UserOAuthConn> findByProviderAndExternalId(OAuth2Provider provider, String providerUserId);
}
