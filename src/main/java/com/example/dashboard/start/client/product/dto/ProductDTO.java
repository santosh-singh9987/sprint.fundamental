package com.example.dashboard.start.client.product.dto;

import lombok.Data;

@Data
public class ProductDTO {
    private Long id;
    private String name;
    private double price;
    private String sku;
}
