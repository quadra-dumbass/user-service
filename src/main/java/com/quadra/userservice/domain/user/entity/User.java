package com.quadra.userservice.domain.user.entity;

import com.quadra.userservice.domain.user.UserRole;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class User {
    private final Long id;
    private final String email;
    private final UserRole userRole;
}
