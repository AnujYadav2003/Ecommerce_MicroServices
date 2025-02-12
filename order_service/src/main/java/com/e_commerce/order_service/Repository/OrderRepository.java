package com.e_commerce.order_service.Repository;

import com.e_commerce.order_service.Model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Long> {
}
