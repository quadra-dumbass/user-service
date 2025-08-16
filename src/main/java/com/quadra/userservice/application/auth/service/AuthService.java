package com.quadra.userservice.application.auth.service;

import com.quadra.userservice.application.auth.dto.RegisterRequestDto;
import com.quadra.userservice.domain.user.entity.User;
import com.quadra.userservice.domain.user.entity.UserCredentials;
import com.quadra.userservice.domain.user.entity.UserOAuthConn;
import com.quadra.userservice.domain.user.enums.UserRole;
import com.quadra.userservice.domain.user.repository.UserCredentialsRepository;
import com.quadra.userservice.domain.user.repository.UserOAuthConnRepository;
import com.quadra.userservice.domain.user.repository.UserRepository;
import com.quadra.userservice.infrastructure.repository.user.JpaUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final UserCredentialsRepository userCredentialsRepository;
    private final UserOAuthConnRepository userOAuthConnRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void register(RegisterRequestDto requestDto) {
        User user = User.builder()
                .name(requestDto.getName())
                .email(requestDto.getEmail())
                .role(UserRole.USER)
                .build();

        Long userId = userRepository.save(user).getId();

        UserCredentials userCredentials = UserCredentials.builder()
                .userId(userId)
                .loginId(requestDto.getLoginId())
                .password(passwordEncoder.encode(requestDto.getPassword()))
                .build();

        userCredentialsRepository.save(userCredentials);
    }
}
