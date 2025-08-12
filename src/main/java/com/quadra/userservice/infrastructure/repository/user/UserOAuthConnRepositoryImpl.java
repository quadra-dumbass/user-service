package com.quadra.userservice.infrastructure.repository.user;

import com.quadra.userservice.domain.security.oauth.enums.OAuth2Provider;
import com.quadra.userservice.domain.user.entity.UserOAuthConn;
import com.quadra.userservice.domain.user.repository.UserOAuthConnRepository;
import com.quadra.userservice.infrastructure.repository.user.mapper.UserOAuthConnEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserOAuthConnRepositoryImpl implements UserOAuthConnRepository {
    private final JpaUserOAuthConnRepository jpaUserOAuthConnRepository;

    @Override
    public Optional<UserOAuthConn> findByProviderAndProviderUserId(OAuth2Provider provider, String providerUserId) {
        return jpaUserOAuthConnRepository.findByProviderAndProviderUserId(provider, providerUserId)
                .map(UserOAuthConnEntityMapper::toDomain);
    }
}
