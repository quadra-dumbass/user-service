package com.quadra.authservice.infrastructure.repository.user;

import com.quadra.authservice.infrastructure.repository.user.schema.UserSchema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaUserRepository extends JpaRepository<UserSchema, Long> {
}
