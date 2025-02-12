package com.e_commerce.order_service.Controller;

import com.e_commerce.order_service.Service.OrderService;
import com.e_commerce.order_service.dto.OrderDto;
import com.e_commerce.order_service.dto.OrderResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/placeOrder")
    @ResponseStatus(HttpStatus.CREATED)
    public String placeOrder(@RequestParam Long productId, @RequestParam Integer quantity) {
        return orderService.placeOrder(productId, quantity);
    }

    @GetMapping("/allOrders")
    @ResponseStatus(HttpStatus.OK)
    public List<OrderResponse> getAllOrderDetails() {
        return orderService.getAllOrders();
    }

    @GetMapping("/getOrderById/{orderId}")
    @ResponseStatus(HttpStatus.OK)
    public OrderResponse getOrderDetails(@PathVariable Long orderId) {
        return orderService.getOrderById(orderId);
    }

    @DeleteMapping("/cancel/{orderId}")
    @ResponseStatus(HttpStatus.OK)
    public String cancelOrder(@PathVariable Long orderId) {
        return orderService.cancelOrder(orderId);
    }

}

