package com.quadra.userservice.infrastructure.repository.user;

import com.quadra.userservice.domain.user.entity.UserOAuthConn;
import com.quadra.userservice.domain.user.enums.OAuth2Provider;
import com.quadra.userservice.domain.user.repository.UserOAuthConnRepository;
import com.quadra.userservice.infrastructure.repository.user.mapper.UserOAuthConnEntityMapper;
import com.quadra.userservice.infrastructure.repository.user.schema.UserOAuthConnSchema;
import com.quadra.userservice.infrastructure.repository.user.schema.UserSchema;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserOAuthConnRepositoryImpl implements UserOAuthConnRepository {
    private final JpaUserOAuthConnRepository jpaUserOAuthConnRepository;
    private final JpaUserRepository jpaUserRepository;

    @Override
    public UserOAuthConn save(UserOAuthConn userOAuthConn) {
        UserSchema userSchema = jpaUserRepository.findById(userOAuthConn.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        UserOAuthConnSchema userOAuthConnSchema = UserOAuthConnEntityMapper.toPersistence(userOAuthConn, userSchema);
        return UserOAuthConnEntityMapper.toDomain(jpaUserOAuthConnRepository.save(userOAuthConnSchema));
    }

    @Override
    public Optional<UserOAuthConn> findByProviderAndExternalId(String provider, String externalId) {
        return jpaUserOAuthConnRepository.findByProviderAndExternalId(OAuth2Provider.valueOf(provider.toUpperCase()), externalId)
                .map(UserOAuthConnEntityMapper::toDomain);
    }
}
