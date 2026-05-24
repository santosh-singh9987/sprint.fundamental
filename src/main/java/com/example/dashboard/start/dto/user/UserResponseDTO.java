package com.example.dashboard.start.dto.user;

import com.example.dashboard.start.enums.BloodGroupType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDTO {

    public Long id;
    public String name;
    public String email;
    public Integer age;
    public BloodGroupType bloodgrouptype;
}
