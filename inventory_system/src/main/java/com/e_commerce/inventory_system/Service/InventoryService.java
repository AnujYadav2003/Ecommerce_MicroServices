package com.e_commerce.inventory_system.Service;

import com.e_commerce.inventory_system.Model.Inventory;
import com.e_commerce.inventory_system.Repository.InventoryRepository;
import com.e_commerce.inventory_system.client.ProductClient;
import com.e_commerce.inventory_system.dto.ProductResponse;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InventoryService {

    private final InventoryRepository inventoryRepository;
    private final ProductClient productClient;

    public boolean isProductExists(Long productId) {
        try {
            ProductResponse product = productClient.getProductById(productId);
            return product != null;
        } catch (FeignException.NotFound e) {
            System.err.println("Product with ID " + productId + " not found in Product Service.");
            return false;
        } catch (Exception e) {
            System.err.println("Error fetching product: " + e.getMessage());
            return false;
        }
    }

    @Transactional
    public String addStock(Long productId, Integer quantity) {
        if (isProductExists(productId)) {
            Optional<Inventory> optionalInventory = inventoryRepository.findByProductId(productId);
            if (optionalInventory.isPresent()) {
                Inventory inventory = optionalInventory.get();
                inventory.setQuantity(inventory.getQuantity() + quantity);
                inventoryRepository.save(inventory);
                return "Stock updated successfully for Product ID: " + productId;
            } else {
                Inventory newInventory = new Inventory();
                newInventory.setProductId(productId);
                newInventory.setQuantity(quantity);
                inventoryRepository.save(newInventory);
                return "New stock added for Product ID: " + productId;
            }
        }
        return "Product with ID " + productId + " not found in Product Service.";
    }

    @Transactional
    public String reduceStock(Long productId, Integer quantity) {
        if (isProductExists(productId)) {
            Inventory inventory = inventoryRepository.findByProductId(productId)
                    .orElseThrow(() -> new RuntimeException("Product with ID " + productId + " not found"));

            if (inventory.getQuantity() >= quantity) {
                inventory.setQuantity(inventory.getQuantity() - quantity);
                inventoryRepository.save(inventory);
                return "Stock reduced successfully for Product ID: " + productId;
            } else {
                throw new RuntimeException("Not enough stock for Product ID: " + productId);
            }
        }
        return "Product with ID " + productId + " not found in Product Service.";
    }


    public List<ProductResponse> getAllInventoryProducts() {

        List<Inventory> inventoryList = inventoryRepository.findAll();

        if (inventoryList == null || inventoryList.isEmpty()) {
            return Collections.emptyList();
        }

        List<ProductResponse> allProducts = productClient.getAllProducts();

        List<ProductResponse> updatedProducts = inventoryList.stream()
                .map(inventory -> {
                    // Find the corresponding product based on productId
                    ProductResponse productResponse = allProducts.stream()
                            .filter(product -> product.getId().equals(inventory.getProductId()))
                            .findFirst()
                            .orElse(null);

                    if (productResponse != null) {
                        if (productResponse.getQuantity() != inventory.getQuantity()) {
                            productResponse.setQuantity(inventory.getQuantity());
                            return productResponse;
                        }
                    }
                    return null;
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        if (updatedProducts.isEmpty()) {
            return Collections.emptyList();
        }

        return updatedProducts;
    }

    public Integer getStock(Long productId) {
        if (isProductExists(productId)) {
            return inventoryRepository.findByProductId(productId)
                    .map(Inventory::getQuantity)
                    .orElseThrow(() -> new RuntimeException("Product with ID " + productId + " not found"));
        }
        return null;
    }
}
