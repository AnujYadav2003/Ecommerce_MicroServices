package com.cart_service.cart_service.Controller;

import com.cart_service.cart_service.Dto.CartRequest;
import com.cart_service.cart_service.Dto.CartResponse;
import com.cart_service.cart_service.Model.Cart;
import com.cart_service.cart_service.Repository.CartRepository;
import com.cart_service.cart_service.Service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping("/addCart/{productId}")
    @ResponseStatus(HttpStatus.OK)
    public CartResponse addProductToCart(@PathVariable String productId) {
        return cartService.addProductToCart(productId);
    }

    @GetMapping("/getProductsFromCart")
    @ResponseStatus(HttpStatus.OK)
    public List<CartResponse> getProductsFromCart()
    {
        return cartService.getProductsFromCart();
    }

    @GetMapping("/getCartById/{cartId}")
    @ResponseStatus(HttpStatus.OK)
    public CartResponse getCartById(@PathVariable String cartId)
    {
        return cartService.getCartById(cartId);
    }

    @DeleteMapping("/deleteProductsFromCart/{cartId}")
    @ResponseStatus(HttpStatus.OK)
    public String deleteProductsFromCart(@PathVariable String cartId)
    {
        return cartService.deleteProductsFromCart(cartId);
    }

    @DeleteMapping("/deleteAllProductsFromCart")
    @ResponseStatus(HttpStatus.OK)
    public String deleteAllProductsFromCart()
    {
        return cartService.deleteAllProductsFromCart();
    }

}
