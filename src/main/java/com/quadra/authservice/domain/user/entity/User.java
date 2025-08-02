package com.quadra.authservice.domain.user.entity;

import com.quadra.authservice.domain.user.UserRole;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class User {
    private final Long id;
    private final String email;
    private final UserRole userRole;
}
