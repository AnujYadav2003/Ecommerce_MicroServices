package com.cart_service.cart_service.Service;

import com.cart_service.cart_service.Client.ProductClient;
import com.cart_service.cart_service.Client.ProductResponse;
import com.cart_service.cart_service.Dto.CartRequest;
import com.cart_service.cart_service.Dto.CartResponse;
import com.cart_service.cart_service.Model.Cart;
import com.cart_service.cart_service.Repository.CartRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class CartService {

    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private final ProductClient productClient;

    public CartResponse addProductToCart(String productId) {
        ProductResponse productResponse = productClient.getProductById(productId);

        if (productResponse != null) {
            Cart newCart = new Cart();
            newCart.setProductId(productId);
            newCart.setTitle(productResponse.getName());
            newCart.setCategory(productResponse.getCategory());
            newCart.setPrice(productResponse.getPrice());
            cartRepository.save(newCart);

            log.info("Product with ID {} added to the cart successfully", productId);
            return mapToCartResponse(newCart);
        } else {
            throw new RuntimeException("No product available with productId: " + productId);
        }
    }


    public List<CartResponse> getProductsFromCart() {

        List<Cart> allCarts = cartRepository.findAll();
        return allCarts.stream()
                .map(this::mapToCartResponse)
                .collect(Collectors.toList());
    }

    public CartResponse getCartById(String cartId) {

        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() ->
                new RuntimeException("Cart not found with ID: " + cartId));

        return mapToCartResponse(cart);
    }

    public String deleteProductsFromCart(String cartId)
    {
        Cart cart=cartRepository.findById(cartId).
                orElseThrow(()->new RuntimeException("Cart not found with ID: " + cartId));
           cartRepository.deleteById(cartId);
        return "Cart deleted Successfully";

    }

    public String deleteAllProductsFromCart()
    {
        List<Cart> cart=cartRepository.findAll();
               if(cart.isEmpty())
                   return "Cart is Already empty";
        cartRepository.deleteAll();
        return "All Cart Products are deleted Successfully";

    }


    private CartResponse mapToCartResponse(Cart cart) {
        return CartResponse.builder()
                .id(cart.getId())
                .productId(cart.getProductId())
                .title(cart.getTitle())
                .category(cart.getCategory())
                .price(cart.getPrice())
                .build();
    }
}
