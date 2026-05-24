package com.example.dashboard.start.client.user;

import com.example.dashboard.start.dto.user.UserRequestDTO;
import com.example.dashboard.start.dto.user.UserResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
@RequiredArgsConstructor
public class UserRestClient {

    private final RestClient userServiceRestClient;

    public UserResponseDTO createUser(UserRequestDTO dto) {

        return userServiceRestClient.post()
                .uri("/user")
                .body(dto)
                .retrieve()
                .onStatus(
                        status -> status.is4xxClientError(),
                        (req, res) -> new RuntimeException("Invalid request sent to User Service")
                )
                .onStatus(
                        status -> status.is5xxServerError(),
                        (req, res) -> new RuntimeException("User Service unavailable")
                )
                .body(UserResponseDTO.class);
    }
}