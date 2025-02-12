package com.e_commerce.product_service.Service;

import com.e_commerce.product_service.Model.Product;
import com.e_commerce.product_service.Repository.ProductRepository;
import com.e_commerce.product_service.dto.ProductResponse;
import com.e_commerce.product_service.dto.RequestProduct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ProductService {

    @Autowired
    private ProductRepository productRepository;


    public ProductResponse createProduct(RequestProduct requestProduct) {
        Product createdProduct = Product.builder()
                .name(requestProduct.getName())
                .description(requestProduct.getDescription())
                .price(requestProduct.getPrice())
                .category(requestProduct.getCategory())
                .build();

        productRepository.save(createdProduct);
        log.info("Product saved successfully");
        return new ProductResponse(createdProduct.getId(), createdProduct.getName(), createdProduct.getDescription(),
                createdProduct.getPrice() ,createdProduct.getCategory());
    }

    public List<ProductResponse> getAllProducts() {
        List<Product> products = productRepository.findAll();

        // Map products to ProductResponse class
        return products.stream()
                .map(this::mapToProductResponse)
                .toList();
    }

    public ProductResponse getProductById(Long productId) {
        return productRepository.findById(productId)
                .map(this::mapToProductResponse)
                .orElseThrow(() -> new RuntimeException("Product not found with ID: " + productId));
    }

    public ProductResponse updateProductById(RequestProduct requestProduct, Long productId) {
        boolean isProductExists = productRepository.findById(productId).isPresent();
        if (isProductExists) {
            Product existingProduct = productRepository.findById(productId).get();

            existingProduct.setName(requestProduct.getName());
            existingProduct.setDescription(requestProduct.getDescription());
            existingProduct.setPrice(requestProduct.getPrice());
            existingProduct.setCategory(requestProduct.getCategory());

            productRepository.save(existingProduct);
            log.info("Product updated successfully");

            return new ProductResponse(existingProduct.getId(), existingProduct.getName(),
                    existingProduct.getDescription(), existingProduct.getPrice(),
                    existingProduct.getCategory());
        } else {
            throw new RuntimeException("Product not found with ID " + productId);
        }
    }


    public  String deleteProductById(Long productId)
    {
        boolean isExists=productRepository.findById(productId).isPresent();
        if(isExists)
        {
            productRepository.deleteById(productId);
            return "Product deleted successfully with id"+productId;
        }
        else {
            throw new RuntimeException("Product is not present with ID:- "+productId);
        }
    }

    public Map<String, List<ProductResponse>> getAllProductsByCategory() {
        List<Product> products = productRepository.findAll();

        return products.stream()
                .collect(Collectors.groupingBy(
                        product -> product.getCategory() != null ? product.getCategory() : "Uncategorized",  // Replace null category with "Uncategorized"
                        Collectors.mapping(this::mapToProductResponse, Collectors.toList())
                ));
    }

    public List<ProductResponse> getProductsByCategory(String category) {

        List<Product> products = productRepository.findByCategory(category);

        if (products.isEmpty()) {
            log.warn("No products found with category: {}", category);
            return List.of();
        }

        return products.stream()
                .map(this::mapToProductResponse)
                .toList();
    }


    public List<ProductResponse> searchProductsByName(String name) {
        List<Product> products = productRepository.findByName(name);

        if (products.isEmpty()) {
            log.warn("No products found with name: {}", name);
        }

        return products.stream()
                .map(this::mapToProductResponse)
                .toList();
    }

    public List<ProductResponse> searchProductsByCategory(String category) {
        List<Product> products = productRepository.findByCategory(category);

        if (products.isEmpty()) {
            log.warn("No products found in category: {}", category);
        }

        return products.stream()
                .map(this::mapToProductResponse)
                .toList();
    }



    private ProductResponse mapToProductResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .category(product.getCategory())
                .build();
    }
}
