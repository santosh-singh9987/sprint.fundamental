package com.example.dashboard.start.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum BloodGroupType {
    A_POSITIVE("A+", "A Positive"),
    A_NEGATIVE("A-", "A Negative"),
    B_POSITIVE("B+", "B Positive"),
    B_NEGATIVE("B-", "B Negative"),
    AB_POSITIVE("AB+", "AB Positive"),
    AB_NEGATIVE("AB-", "AB Negative"),
    O_POSITIVE("O+", "O Positive"),
    O_NEGATIVE("O-", "O Negative");

    private final String shortName;
    private final String fullName;

    BloodGroupType(String shortName, String fullName) {
        this.shortName = shortName;
        this.fullName = fullName;
    }

    public String getShortName() {
        return shortName;
    }

    @JsonValue
    public String getFullName() {
        return fullName;
    }
}