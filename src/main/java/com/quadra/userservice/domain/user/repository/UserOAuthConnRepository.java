package com.quadra.userservice.domain.user.repository;

import com.quadra.userservice.domain.security.oauth.enums.OAuth2Provider;
import com.quadra.userservice.domain.user.entity.UserOAuthConn;

import java.util.Optional;

public interface UserOAuthConnRepository {
    Optional<UserOAuthConn> findByProviderAndProviderUserId(OAuth2Provider provider, String providerUserId);
}
