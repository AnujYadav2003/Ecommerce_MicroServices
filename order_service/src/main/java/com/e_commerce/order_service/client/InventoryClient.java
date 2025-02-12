package com.e_commerce.order_service.client;

import com.e_commerce.order_service.dto.ProductResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "inventory", url = "http://localhost:8084")
public interface InventoryClient {

    @GetMapping("/api/inventory/all")
    List<ProductResponse> getAllInventoryProducts();

    @PutMapping("/api/inventory/reduce")
    String reduceStock(@RequestParam Long productId, @RequestParam Integer quantity);
}

