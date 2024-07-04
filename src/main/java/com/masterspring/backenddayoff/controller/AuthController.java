package com.masterspring.backenddayoff.controller;

import com.masterspring.backenddayoff.dto.request.AuthRequest;
import com.masterspring.backenddayoff.dto.response.AuthResponse;
import com.masterspring.backenddayoff.service.AuthService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@Tag(name = "Authentication")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/auth/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody AuthRequest authRequest) {
        return ResponseEntity.ok(authService.login(authRequest));
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<AuthResponse> getUserInfo(@PathVariable Long userId) {
        return ResponseEntity.ok(authService.getUserInfo(userId));
    }
}
