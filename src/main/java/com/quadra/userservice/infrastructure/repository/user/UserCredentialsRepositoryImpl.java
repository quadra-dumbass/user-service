package com.quadra.userservice.infrastructure.repository.user;

import com.quadra.userservice.domain.user.entity.UserCredentials;
import com.quadra.userservice.domain.user.repository.UserCredentialsRepository;
import com.quadra.userservice.infrastructure.repository.user.mapper.UserCredentialsEntityMapper;
import com.quadra.userservice.infrastructure.repository.user.schema.UserCredentialsSchema;
import com.quadra.userservice.infrastructure.repository.user.schema.UserSchema;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserCredentialsRepositoryImpl implements UserCredentialsRepository {
    private final JpaUserCredentialsRepository jpaUserCredentialsRepository;
    private final JpaUserRepository jpaUserRepository;


    @Override
    public UserCredentials save(UserCredentials userCredentials) {
        UserSchema userSchema = jpaUserRepository.findById(userCredentials.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        UserCredentialsSchema userCredentialsSchema = UserCredentialsEntityMapper.toPersistence(userCredentials, userSchema);
        return UserCredentialsEntityMapper.toDomain(jpaUserCredentialsRepository.save(userCredentialsSchema));
    }

    @Override
    public Optional<UserCredentials> findByLoginId(String loginId) {
        return jpaUserCredentialsRepository.findByLoginId(loginId).map(UserCredentialsEntityMapper::toDomain);
    }
}
