package com.e_commerce.product_service.Repository;

import com.e_commerce.product_service.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long> {

    List<Product> findByCategory(String category);
    List<Product> findByName(String name);
}
