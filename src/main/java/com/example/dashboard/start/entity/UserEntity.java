package com.example.dashboard.start.entity;

import com.example.dashboard.start.enums.BloodGroupType;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
//@Audited
@Table(name = "myuser")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private Integer age;
    private String password;
    @Enumerated(EnumType.STRING)
    private BloodGroupType bloodGroupType;
    private String role = "ADMIN";
    @OneToMany(
            mappedBy = "user",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<OrderManagementEntity> order = new ArrayList<>();
}
