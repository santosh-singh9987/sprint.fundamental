package com.example.dashboard.start.repository;

import com.example.dashboard.start.entity.OrderManagementEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderManagementRepository extends JpaRepository<OrderManagementEntity, Long> {

}