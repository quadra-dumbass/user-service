package com.quadra.userservice.infrastructure.repository.user.mapper;

import com.quadra.userservice.domain.user.entity.UserOAuthConn;
import com.quadra.userservice.domain.user.enums.OAuth2Provider;
import com.quadra.userservice.infrastructure.repository.user.schema.UserOAuthConnSchema;
import com.quadra.userservice.infrastructure.repository.user.schema.UserSchema;

public class UserOAuthConnEntityMapper {
    public static UserOAuthConn toDomain(UserOAuthConnSchema userOAuthConnSchema){
        return UserOAuthConn.builder()
                .userId(userOAuthConnSchema.getUser().getId())
                .provider(userOAuthConnSchema.getProvider().getValue())
                .externalId(userOAuthConnSchema.getExternalId())
                .build();
    }

    public static UserOAuthConnSchema toPersistence(UserOAuthConn userOAuthConn, UserSchema userSchema){
        return UserOAuthConnSchema.builder()
                .user(userSchema)
                .provider(OAuth2Provider.valueOf(userOAuthConn.getProvider().toUpperCase()))
                .externalId(userOAuthConn.getExternalId())
                .build();
    }
}
