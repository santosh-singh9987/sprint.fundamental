package com.example.dashboard.start.dto.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderManagementDTO {
    private String sku;
    private Integer quantity;
    private double price;
    private Long userId;
}
