////package com.e_commerce.order_service.dto;
////
////import lombok.*;
////
////import java.math.BigDecimal;
////@AllArgsConstructor
////@NoArgsConstructor
////@Setter
////@Getter
////@Builder
////public class OrderResponse {
////    private Long id;
////    private String orderNumber;
////    private String skuCode;
////    private BigDecimal price;
////    private Integer quantity;
////    private String orderStatus;
////}
//
//
//package com.e_commerce.order_service.dto;
//
//import lombok.*;
//
//import java.math.BigDecimal;
//
//@AllArgsConstructor
//@NoArgsConstructor
//@Setter
//@Getter
//@Builder
//public class OrderResponse {
//    private Long id;            // Order ID
//    private String orderNumber; // Unique order number
//    private Long productId;     // Reference to the product (replacing skuCode)
//    private BigDecimal price;   // Price of the order
//    private Integer quantity;   // Quantity of the product ordered
//    private String orderStatus; // Status of the order (pending, shipped, etc.)
//}

package com.e_commerce.order_service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponse {

    private Long id;
    private String orderNumber;
    private Integer quantity;

    private ProductResponse product;
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ProductResponse {
        private Long id;
        private String name;
        private BigDecimal price;
    }
}

