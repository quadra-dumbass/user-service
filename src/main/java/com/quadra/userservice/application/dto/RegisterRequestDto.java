package com.quadra.userservice.application.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRequestDto {
    private String email;
    private String password;
    private String provider;

    @Builder
    public RegisterRequestDto(String email, String password, String provider) {
        this.email = email;
        this.password = password;
        this.provider = provider;
    }
}
