package com.quadra.userservice.infrastructure.repository.user;

import com.quadra.userservice.infrastructure.repository.user.schema.UserCredentialsSchema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JpaUserCredentialsRepository extends JpaRepository<UserCredentialsSchema, Long> {

    Optional<UserCredentialsSchema> findByLoginId(String loginId);
}
