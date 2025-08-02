package com.quadra.userservice.domain.user;

public enum UserRole {
    ADMIN("ROLE_ADMIN"), // admin user, use this only for tests.
    USER("ROLE_USER"); // normal user

    private final String authority;

    UserRole(String authority) {
        this.authority = authority;
    }

    public String getAuthority() {
        return this.authority;
    }
}
