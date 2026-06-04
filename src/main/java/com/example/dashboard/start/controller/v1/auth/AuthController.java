package com.example.dashboard.start.controller.v1.auth;

import com.example.dashboard.start.advices.ApiResponse;
import com.example.dashboard.start.dto.auth.AuthResponseDTO;
import com.example.dashboard.start.dto.auth.LoginRequestDTO;
import com.example.dashboard.start.dto.auth.SignupRequestDTO;
import com.example.dashboard.start.service.auth.AuthService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public ApiResponse<String> signup(@RequestBody SignupRequestDTO request) {
        authService.signUp(request);
        return new ApiResponse<>("User registered successfully");
    }

    @PostMapping("/login")
    public ApiResponse<AuthResponseDTO> login(@RequestBody LoginRequestDTO request, HttpServletResponse response) {
        AuthResponseDTO authResponseDTO = authService.login(request);
        Cookie cookie = new Cookie("refresh_token", authResponseDTO.getRefreshToken());
        cookie.setHttpOnly(true);
        cookie.setSecure("production".equals("prod"));
        response.addCookie(cookie);
        return new ApiResponse<>(authResponseDTO);
    }

    @PostMapping("/refresh")
    public ApiResponse<AuthResponseDTO> refresh(@RequestParam String refreshToken) {
        return new ApiResponse<>(authService.refresh(refreshToken));
    }

    @GetMapping("/debug")
    public String debug() {
        return SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();
    }
}