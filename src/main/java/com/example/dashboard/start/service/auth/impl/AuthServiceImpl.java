package com.example.dashboard.start.service.auth.impl;

import com.example.dashboard.start.dto.auth.AuthResponseDTO;
import com.example.dashboard.start.dto.auth.LoginRequestDTO;
import com.example.dashboard.start.dto.auth.SignupRequestDTO;
import com.example.dashboard.start.entity.RefreshToken;
import com.example.dashboard.start.entity.UserEntity;
import com.example.dashboard.start.repository.UserRepository;
import com.example.dashboard.start.security.util.JwtUtil;
import com.example.dashboard.start.service.auth.AuthService;
import com.example.dashboard.start.service.auth.RefreshTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final RefreshTokenService refreshTokenService;

    @Override
    public AuthResponseDTO login(LoginRequestDTO request) {
        UserEntity user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid credentials"));

        String role = user.getRole();
        System.out.println("role: " + role);
        if (!passwordEncoder.matches(
                request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }
        String accessToken = jwtUtil.generateAccessToken(user.getEmail(),role);
        RefreshToken refreshToken =
                refreshTokenService.createRefreshToken(user.getEmail());
        return new AuthResponseDTO(accessToken, refreshToken.getToken());
    }

    @Override
    public void signUp(SignupRequestDTO request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists");
        }
        UserEntity user = new UserEntity();
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        userRepository.save(user);
    }

    @Override
    public AuthResponseDTO refresh(String token) {
        RefreshToken refreshToken =
                refreshTokenService.validateRefreshToken(token);
        String role = jwtUtil.extractRole(token);
        String newAccessToken =
                jwtUtil.generateAccessToken(refreshToken.getUsername(),role);
        return new AuthResponseDTO(newAccessToken, refreshToken.getToken());
    }
}
