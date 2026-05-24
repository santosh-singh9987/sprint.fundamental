package com.example.dashboard.start.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Entity
@Setter
public class RefreshToken {

    @Id
    @GeneratedValue
    private Long id;

    private String token;

    private String username;

    private Instant expiryDate;
}
