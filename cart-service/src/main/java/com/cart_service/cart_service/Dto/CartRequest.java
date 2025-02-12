package com.cart_service.cart_service.Dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CartRequest {
    @Column(name = "productId")
    private String productId;
    private String title;
    private String category;
    private BigDecimal price;
}
