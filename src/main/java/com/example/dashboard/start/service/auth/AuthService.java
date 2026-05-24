package com.example.dashboard.start.service.auth;

import com.example.dashboard.start.dto.auth.AuthResponseDTO;
import com.example.dashboard.start.dto.auth.LoginRequestDTO;
import com.example.dashboard.start.dto.auth.SignupRequestDTO;

public interface AuthService {

    AuthResponseDTO login(LoginRequestDTO request);

    AuthResponseDTO refresh(String refreshToken);

    void signUp(SignupRequestDTO request);
}