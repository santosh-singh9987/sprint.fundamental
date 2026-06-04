package com.example.dashboard.start.entity;

import com.example.dashboard.start.audit.Auditable;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "order_management")
//@Audited
public class OrderManagementEntity extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String sku;
    private Integer quantity;
    private double price;
    @Column(nullable = false)
    private Boolean isAvailable = true;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;
}
