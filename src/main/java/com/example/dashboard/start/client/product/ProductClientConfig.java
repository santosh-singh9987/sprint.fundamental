package com.example.dashboard.start.client.product;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration

public class ProductClientConfig {

    @Bean("productRestClient")
    public RestClient productRestClient() {
        return RestClient.builder()
                .baseUrl("http://localhost:8081/api/v1/products")
                .build();
    }
}
