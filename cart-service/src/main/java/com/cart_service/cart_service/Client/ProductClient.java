package com.cart_service.cart_service.Client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(value = "product-service",url="http://localhost:8081")
public interface ProductClient {
    @RequestMapping(method= RequestMethod.GET,value = "/api/products/getById/{productId}")
    ProductResponse getProductById(@PathVariable String productId);
}
