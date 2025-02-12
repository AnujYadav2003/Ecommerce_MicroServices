package com.e_commerce.inventory_system.Controller;

import com.e_commerce.inventory_system.Service.InventoryService;
import com.e_commerce.inventory_system.dto.ProductResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public String addStock(@RequestParam Long productId, @RequestParam Integer quantity) {
        return inventoryService.addStock(productId, quantity);
    }

    @PutMapping("/reduce")
    @ResponseStatus(HttpStatus.OK)
    public String reduceStock(@RequestParam Long productId, @RequestParam Integer quantity) {
        return inventoryService.reduceStock(productId, quantity);
    }

@GetMapping("/all")
@ResponseStatus(HttpStatus.OK)
public List<ProductResponse> getAllInventoryProducts() {
    return inventoryService.getAllInventoryProducts();
}

    @GetMapping("/stock/{productId}")
    @ResponseStatus(HttpStatus.OK)
    public Integer getStock(@PathVariable Long productId) {
        return inventoryService.getStock(productId);
    }
}
