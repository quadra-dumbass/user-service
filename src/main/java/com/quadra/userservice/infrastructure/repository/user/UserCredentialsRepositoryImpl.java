package com.quadra.userservice.infrastructure.repository.user;

import com.quadra.userservice.domain.user.entity.UserCredentials;
import com.quadra.userservice.domain.user.repository.UserCredentialsRepository;
import com.quadra.userservice.infrastructure.repository.user.mapper.UserCredentialsEntityMapper;
import com.quadra.userservice.infrastructure.repository.user.schema.UserCredentialsSchema;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserCredentialsRepositoryImpl implements UserCredentialsRepository {
    private final JpaUserCredentialsRepository jpaUserCredentialsRepository;


    @Override
    public Optional<UserCredentials> findByLoginId(String loginId) {
        return jpaUserCredentialsRepository.findByLoginId(loginId).map(UserCredentialsEntityMapper::toDomain);
    }
}
