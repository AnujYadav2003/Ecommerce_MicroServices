package com.e_commerce.product_service.Controller;

import com.e_commerce.product_service.Service.ProductService;
import com.e_commerce.product_service.dto.ProductResponse;
import com.e_commerce.product_service.dto.RequestProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ProductResponse createProduct(@RequestBody RequestProduct requestProduct)
    {
      return productService.createProduct(requestProduct);
    }

    @GetMapping("/getAllProducts")
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponse> getAllProducts()
    {
        return productService.getAllProducts();
    }



    @GetMapping("/getById/{productId}")
    @ResponseStatus(HttpStatus.OK)
    public ProductResponse getProductById(@PathVariable Long productId)
    {
        return productService.getProductById(productId);
    }

    @PutMapping("/updateById/{productId}")
    @ResponseStatus(HttpStatus.OK)
    public ProductResponse updateProductById(@RequestBody RequestProduct requestProduct,@PathVariable Long productId)
    {
        return productService.updateProductById(requestProduct,productId);
    }

    @DeleteMapping("/deleteById/{productId}")
    @ResponseStatus(HttpStatus.OK)
    public String deleteProductById(@PathVariable Long productId)
    {
       return productService.deleteProductById(productId);

    }

    @GetMapping("/groupByCategory")
    @ResponseStatus(HttpStatus.OK)
    public Map<String, List<ProductResponse>> getProductsGroupedByCategory() {
        return productService.getAllProductsByCategory();
    }

    @GetMapping("/getProductsByCategory/{category}")
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponse> getProductsByCategory(@PathVariable String category) {
        return productService.getProductsByCategory(category);
    }

    @GetMapping("/searchByName/{name}")
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponse> searchProductsByName(@PathVariable String name) {
        return productService.searchProductsByName(name);
    }

    @GetMapping("/searchByCategory/{category}")
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponse> searchProductsByCategory(@PathVariable String category) {
        return productService.searchProductsByCategory(category);
    }


}
