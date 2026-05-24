package com.example.dashboard.start.client.product;

import com.example.dashboard.start.client.product.dto.PageResponse;
import com.example.dashboard.start.client.product.dto.ProductDTO;
import com.example.dashboard.start.exception.ExternalServiceException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
@RequiredArgsConstructor
public class ProductClient {

    private final RestClient productRestClient;
    private int page = 0;
    private int pageSize = 5;
    private final Logger log =  LoggerFactory.getLogger(ProductClient.class);
    public PageResponse<ProductDTO> fetchProducts(String authHeader) {
        try {
            PageResponse<ProductDTO> response = productRestClient.get()
                    .uri(uriBuilder -> uriBuilder
                            .path("/page-sort")
                            .queryParam("page",page)
                            .queryParam("pageSize",pageSize)
                            .build()
                    )
                    .header("Authorization", authHeader)
                    .retrieve()
                    .onStatus(
                            status -> status.is4xxClientError(),
                            (req, res) -> {
                                log.warn("4xx error from Product Service: status={}", res.getStatusCode());
                                throw new ExternalServiceException(
                                        "Invalid request sent to Product Service"
                                );
                            }
                    )
                    .onStatus(
                            status -> status.is5xxServerError(),
                            (req, res) -> {
                                log.error("5xx error from Product Service: status={}", res.getStatusCode());
                                throw new ExternalServiceException(
                                        "Product Service is unavailable"
                                );
                            }
                    )
                    .body(new ParameterizedTypeReference<>() {});
            return response;
        } catch (Exception e) {
            log.error("Error while calling Product Service", e);
            throw e;
        }
    }
}
