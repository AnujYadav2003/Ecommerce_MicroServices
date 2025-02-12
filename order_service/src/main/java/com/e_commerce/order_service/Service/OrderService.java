package com.e_commerce.order_service.Service;

import com.e_commerce.order_service.Model.Order;
import com.e_commerce.order_service.Repository.OrderRepository;
import com.e_commerce.order_service.client.InventoryClient;
import com.e_commerce.order_service.dto.OrderDto;
import com.e_commerce.order_service.dto.OrderResponse;
import com.e_commerce.order_service.dto.ProductResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final InventoryClient inventoryClient;

    // Place an order after checking inventory stock
    public String placeOrder(Long productId, Integer quantity) {
        // Fetch all products in inventory
        List<ProductResponse> inventoryProducts = inventoryClient.getAllInventoryProducts();

        // Check if the product exists in inventory
        Optional<ProductResponse> productOpt = inventoryProducts.stream()
                .filter(product -> product.getId().equals(productId))
                .findFirst();

        if (productOpt.isEmpty()) {
            return "Product ID " + productId + " not found in inventory.";
        }

        ProductResponse product = productOpt.get();

        if (product.getQuantity() < quantity) {
            return "Not enough stock for Product ID " + productId + ". Available quantity: " + product.getQuantity();
        }

        Order newOrder = new Order();
        newOrder.setOrderNumber(UUID.randomUUID().toString()); // Order number as UUID string
        newOrder.setProductId(productId);
        newOrder.setQuantity(quantity);
        orderRepository.save(newOrder);

        inventoryClient.reduceStock(productId, quantity);

        log.info("Order placed successfully for Product ID: " + productId);
        return "Order placed successfully for Product ID: " + productId;
    }

    // Get the status of an order
//    public String getOrderStatus(Long orderId) {
//        Optional<Order> order = orderRepository.findById(orderId);
//        if (order.isPresent()) {
//            return "Order Status: " + order.get().getOrderStatus();
//        } else {
//            return "Order with ID " + orderId + " not found.";
//        }
//    }
    public String cancelOrder(Long orderId) {
        Optional<Order> order = orderRepository.findById(orderId);
        if (order.isPresent()) {
            Order orderToCancel = order.get();
            orderRepository.delete(orderToCancel);

            inventoryClient.reduceStock(orderToCancel.getProductId(), -orderToCancel.getQuantity());

            log.info("Order " + orderId + " cancelled and inventory updated.");
            return "Order has been cancelled for Product ID: " + orderToCancel.getProductId() + " and inventory updated.";
        } else {
            return "Order with ID " + orderId + " not found.";
        }
    }

    public List<OrderResponse> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        return orders.stream()
                .map(this::mapToOrderResponse)
                .toList();
    }

    public OrderResponse getOrderById(Long orderId) {
        Optional<Order> order = orderRepository.findById(orderId);
        if (order.isPresent()) {
            return mapToOrderResponse(order.get());
        } else {
            throw new RuntimeException("Order not found with ID: " + orderId);
        }
    }

    private OrderResponse mapToOrderResponse(Order order) {

        List<ProductResponse> inventoryProducts = inventoryClient.getAllInventoryProducts();

        Optional<ProductResponse> productOpt = inventoryProducts.stream()
                .filter(product -> product.getId().equals(order.getProductId()))
                .findFirst();

        if (productOpt.isEmpty()) {
            throw new RuntimeException("Product ID " + order.getProductId() + " not found in inventory.");
        }

        ProductResponse product = productOpt.get();
        return OrderResponse.builder()
                .id(order.getId())
                .orderNumber(order.getOrderNumber())
                .quantity(order.getQuantity())
//                .orderStatus(order.getOrderStatus())
                .product(new OrderResponse.ProductResponse(
                        product.getId(),
                        product.getName(),
                        product.getPrice()
                ))
                .build();
    }
}

