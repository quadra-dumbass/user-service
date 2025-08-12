package com.quadra.userservice.infrastructure.repository.user.mapper;

import com.quadra.userservice.domain.user.entity.User;
import com.quadra.userservice.domain.user.enums.UserRole;
import com.quadra.userservice.infrastructure.repository.user.schema.UserSchema;

public class UserEntityMapper {

    public static User toDomain(UserSchema userSchema) {
        UserRole userRole = userSchema.getIsAdmin() ? UserRole.ADMIN : UserRole.USER;
        return User.builder()
                .id(userSchema.getId())
                .name(userSchema.getName())
                .email(userSchema.getEmail())
                .role(userRole)
                .build();
    }

    public static UserSchema toPersistence(User user) {
        boolean isAdmin = user.getRole() == UserRole.ADMIN;
        return UserSchema.builder()
                .name(user.getName())
                .email(user.getEmail())
                .isAdmin(isAdmin)
                .build();
    }
}
