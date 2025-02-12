package com.cart_service.cart_service.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CartResponse {
    private Long id;
    private String title;
    private String productId;
    private BigDecimal price;
    private String category;
}
