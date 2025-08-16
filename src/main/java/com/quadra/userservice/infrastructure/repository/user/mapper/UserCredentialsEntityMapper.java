package com.quadra.userservice.infrastructure.repository.user.mapper;

import com.quadra.userservice.domain.user.entity.User;
import com.quadra.userservice.domain.user.entity.UserCredentials;
import com.quadra.userservice.infrastructure.repository.user.schema.UserCredentialsSchema;
import com.quadra.userservice.infrastructure.repository.user.schema.UserSchema;

public class UserCredentialsEntityMapper {
    public static UserCredentials toDomain(UserCredentialsSchema userCredentialsSchema) {
        return UserCredentials.builder()
                .userId(userCredentialsSchema.getId())
                .loginId(userCredentialsSchema.getLoginId())
                .password(userCredentialsSchema.getPassword())
                .build();
    }

    public static UserCredentialsSchema toPersistence(UserCredentials userCredentials, UserSchema userSchema) {
        return UserCredentialsSchema.builder()
                .user(userSchema)
                .loginId(userCredentials.getLoginId())
                .password(userCredentials.getPassword())
                .build();
    }
}
