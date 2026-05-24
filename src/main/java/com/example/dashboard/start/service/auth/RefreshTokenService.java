package com.example.dashboard.start.service.auth;

import com.example.dashboard.start.entity.RefreshToken;
import com.example.dashboard.start.repository.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class RefreshTokenService {

    private final RefreshTokenRepository repository;

    private static final long REFRESH_TOKEN_EXPIRY =
            60L * 60 * 24 * 7; // 7 days

    public RefreshToken createRefreshToken(String username) {
        repository.deleteByUsername(username); // one active token per user

        RefreshToken token = new RefreshToken();
        token.setToken(UUID.randomUUID().toString());
        token.setUsername(username);
        token.setExpiryDate(Instant.now().plusSeconds(REFRESH_TOKEN_EXPIRY));
        return repository.save(token);
    }

    @Transactional(readOnly = true)
    public RefreshToken validateRefreshToken(String token) {
        RefreshToken refreshToken = repository.findByToken(token)
                .orElseThrow(() -> new RuntimeException("Invalid refresh token"));

        if (refreshToken.getExpiryDate().isBefore(Instant.now())) {
            repository.delete(refreshToken);
            throw new RuntimeException("Refresh token expired");
        }

        return refreshToken;
    }
}
