package com.quadra.userservice.application.dto;

import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.logging.log4j.core.config.plugins.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
public class LoginRequestDto {
    @NotBlank(message = "email cannot be empty.")
    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$",
            message = "invalid email format")
    private String email;

    @NotBlank(message = "password cannot be empty.")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[!@#$%^&*,./?\\-_+=])[A-Za-z\\d!@#$%^&*,./?\\-_+=]{8,}$",
            message = "password must be at least 8 characters long and include at least one letter, one number, and one special character.")
    // allowed characters : '!', '@', '#', '$', '%', '^', '&', ',', '.', '/', '?', '-', '_', '+', '='
    private String password;
}