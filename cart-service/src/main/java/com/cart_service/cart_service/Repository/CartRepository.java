package com.cart_service.cart_service.Repository;

import com.cart_service.cart_service.Model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart,String> {
}
