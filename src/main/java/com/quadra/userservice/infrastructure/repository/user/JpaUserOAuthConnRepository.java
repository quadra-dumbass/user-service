package com.quadra.userservice.infrastructure.repository.user;

import com.quadra.userservice.application.security.oauth.enums.OAuth2Provider;
import com.quadra.userservice.infrastructure.repository.user.schema.UserOAuthConnSchema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JpaUserOAuthConnRepository extends JpaRepository<UserOAuthConnSchema, Long> {
    Optional<UserOAuthConnSchema> findByProviderAndProviderUserId(OAuth2Provider provider, String providerUserId);
}
