package com.quadra.userservice.infrastructure.repository.user;

import com.quadra.userservice.domain.user.entity.User;
import com.quadra.userservice.domain.user.repository.UserRepository;
import com.quadra.userservice.infrastructure.repository.user.mapper.UserEntityMapper;
import com.quadra.userservice.infrastructure.repository.user.schema.UserSchema;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {
    private final JpaUserRepository jpaUserRepository;

    @Override
    public Optional<User> findByEmail(String email) {
        return Optional.empty();
    }

    @Override
    public User save(User user) {
        UserSchema userSchema = UserEntityMapper.toPersistence(user);
        return UserEntityMapper.toDomain(jpaUserRepository.save(userSchema));
    }

    @Override
    public Optional<User> findById(Long id) {
        return jpaUserRepository.findById(id).map(UserEntityMapper::toDomain);
    }
}
