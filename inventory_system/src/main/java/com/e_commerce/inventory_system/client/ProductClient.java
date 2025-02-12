package com.e_commerce.inventory_system.client;

import com.e_commerce.inventory_system.dto.ProductResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "product-service", url = "http://localhost:8081/api/products")
public interface ProductClient {
    @GetMapping("/getById/{productId}")
    ProductResponse getProductById(@PathVariable Long productId);
    @GetMapping("/getAllProducts")
    List<ProductResponse> getAllProducts();
}

