package com.example.dashboard.start.controller.v1.order;

import com.example.dashboard.start.client.product.dto.PageResponse;
import com.example.dashboard.start.client.product.dto.ProductDTO;
import com.example.dashboard.start.dto.order.OrderManagementDTO;
import com.example.dashboard.start.service.OrdermanagementInterface;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/order")
@RequiredArgsConstructor
@Slf4j
@ToString
public class OrderController {
    private final OrdermanagementInterface ordermanagementInterface;
    private final Logger logger = LoggerFactory.getLogger(OrderController.class);

    @GetMapping
    public ResponseEntity<List<OrderManagementDTO>> getOrder(
    ){
        List<OrderManagementDTO> getOrders =  ordermanagementInterface.findAllOrderBy();
        return ResponseEntity.ok(getOrders);
    }

    @PostMapping("/create")
    public ResponseEntity<OrderManagementDTO> create(@RequestBody OrderManagementDTO orderManagementDTO
    ){
        OrderManagementDTO createOrder =  ordermanagementInterface.CreateOrder(orderManagementDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createOrder);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/product")
    public PageResponse<ProductDTO> fetchAllProduct(HttpServletRequest request) {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        authentication.getAuthorities()
                .forEach(a -> logger.info("Authority: {}", a.getAuthority()));

        if (authentication != null && authentication.getDetails() instanceof WebAuthenticationDetails details) {
            String clientIp = details.getRemoteAddress();
            logger.info("Client IP from SecurityContext: {}", clientIp);
        }

        String authHeader = request.getHeader("Authorization");
        return ordermanagementInterface.fetchAllProducts(authHeader);
    }


}
