package com.example.dashboard.start.service;

import com.example.dashboard.start.client.product.ProductClient;
import com.example.dashboard.start.client.product.dto.PageResponse;
import com.example.dashboard.start.client.product.dto.ProductDTO;
import com.example.dashboard.start.dto.order.OrderManagementDTO;
import com.example.dashboard.start.entity.OrderManagementEntity;
import com.example.dashboard.start.entity.UserEntity;
import com.example.dashboard.start.repository.OrderManagementRepository;
import com.example.dashboard.start.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service @RequiredArgsConstructor
public class OrderManagementService implements OrdermanagementInterface{

    private final OrderManagementRepository orderManagementRepository;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final ProductClient productClient;

    private final Logger logger = LoggerFactory.getLogger(OrderManagementService.class);

    @Override
    public List<OrderManagementDTO> findAllOrderBy() {
        logger.debug("findAllOrderBy");
        List<OrderManagementEntity> orderResponse = orderManagementRepository.findAll();
        return orderResponse.
                stream()
                .map(entity -> modelMapper.map(entity, OrderManagementDTO.class))
                .toList();
    }

    @Override
    public OrderManagementDTO CreateOrder(OrderManagementDTO dto) {

        try {
            UserEntity userEntity = userRepository.findById(dto.getUserId()).orElseThrow(
                    () -> new RuntimeException("User not found!" + dto.getUserId())
            );
            OrderManagementEntity orderEntity = new OrderManagementEntity();
            orderEntity.setUser(userEntity);
            orderEntity.setSku(dto.getSku());
            orderEntity.setQuantity(dto.getQuantity());
            orderEntity.setPrice(dto.getPrice());
            OrderManagementEntity orderResponseEntity = orderManagementRepository.save(orderEntity);
            return modelMapper.map(orderResponseEntity,OrderManagementDTO.class);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public PageResponse<ProductDTO> fetchAllProducts(String authHeader) {
        return productClient.fetchProducts(authHeader);
    }
}
