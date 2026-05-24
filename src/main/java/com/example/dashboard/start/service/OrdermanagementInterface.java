package com.example.dashboard.start.service;

import com.example.dashboard.start.client.product.dto.PageResponse;
import com.example.dashboard.start.client.product.dto.ProductDTO;
import com.example.dashboard.start.dto.order.OrderManagementDTO;

import java.util.List;

public interface OrdermanagementInterface {
    List<OrderManagementDTO> findAllOrderBy();

    OrderManagementDTO CreateOrder(OrderManagementDTO orderManagementDTO);

    PageResponse<ProductDTO> fetchAllProducts(String authHeader);
}
