package com.example.dashboard.start.client.product.dto;

import lombok.Data;
import java.util.List;

@Data
public class PageResponse<T> {
    private List<T> data;
    private int page;
    private int size;
    private long totalElements;
    private int totalPages;
    private boolean last;
}
