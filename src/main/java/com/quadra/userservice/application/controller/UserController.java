package com.quadra.userservice.application.controller;

import com.quadra.userservice.application.dto.LoginRequestDto;
import com.quadra.userservice.application.dto.RegisterRequestDto;
import com.quadra.userservice.application.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class UserController {
    private final UserService userService;

    @GetMapping("/register")
    public ResponseEntity<?> register() {
        return ResponseEntity.ok("register");
    }

    @GetMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequestDto loginRequestDto) {

        return ResponseEntity.ok("login");
    }

    @GetMapping("/reissue")
    public ResponseEntity<?> reissue() {
        return ResponseEntity.ok("reissue");
    }
}
