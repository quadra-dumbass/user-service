package com.quadra.userservice.application.auth.controller;

import com.quadra.userservice.application.auth.dto.LoginRequestDto;
import com.quadra.userservice.application.auth.dto.RegisterRequestDto;
import com.quadra.userservice.application.auth.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;

    @GetMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequestDto requestDto) {
        authService.register(requestDto);
        return ResponseEntity.ok("register");
    }

    @GetMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequestDto loginRequestDto) {
        // authService.login(requestDto);
        return ResponseEntity.ok("login");
    }
    

    @GetMapping("/issue")
    public ResponseEntity<?> issue(@AuthenticationPrincipal Object principal) {
        // principal -> jwt principal -> 여기서 재발급

        return ResponseEntity.ok("reissue");
    }

}
