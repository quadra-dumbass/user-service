package com.quadra.userservice.application.controller;

import com.quadra.userservice.application.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<?> login() {
        return ResponseEntity.ok("login");
    }

    @GetMapping("/reissue")
    public ResponseEntity<?> reissue() {
        return ResponseEntity.ok("reissue");
    }
}
