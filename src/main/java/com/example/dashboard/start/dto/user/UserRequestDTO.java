package com.example.dashboard.start.dto.user;

import com.example.dashboard.start.enums.BloodGroupType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor // not require for out case as we are not making setting parameter to an object
public class UserRequestDTO {

    @NotBlank(message = "Name cannot be empty")
    public String name;
    @Email(message = "Invalid email format")
    @NotBlank(message = "Email cannot be empty")
    public String email;
    @NotNull
    @Min(value = 1, message = "Age must be greater than zero")
    public Integer age;
    @NotNull
    @Enumerated(EnumType.STRING)
    public BloodGroupType bloodGroupType;
}