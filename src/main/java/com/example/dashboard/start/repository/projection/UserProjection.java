package com.example.dashboard.start.repository.projection;


import com.example.dashboard.start.enums.BloodGroupType;

public interface UserProjection {
    Long getId();
    String getName();
    String getEmail();
    Integer getAge();
    BloodGroupType getBloodGroupType();
}
