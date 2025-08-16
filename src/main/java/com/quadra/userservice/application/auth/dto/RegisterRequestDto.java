package com.quadra.userservice.application.auth.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RegisterRequestDto {
    private String name;
    private String loginId;
    private String email;
    private String password;
    // private String provider;
}
