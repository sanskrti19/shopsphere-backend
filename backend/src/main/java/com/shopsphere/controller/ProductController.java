package com.shopsphere.controller;

import com.shopsphere.model.Product;
import com.shopsphere.service.ProductService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")

@RequiredArgsConstructor

@CrossOrigin(origins = "http://localhost:5173")

public class ProductController {

    private final ProductService productService;

    // CREATE PRODUCT
    @PostMapping("/add")
    public ResponseEntity<Product> addProduct(
            @RequestBody Product product
    ) {

        return ResponseEntity.ok(
                productService.addProduct(product)
        );
    }

    // GET ALL PRODUCTS
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {

        return ResponseEntity.ok(
                productService.getAllProducts()
        );
    }

    // GET PRODUCT BY ID
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(
            @PathVariable String id
    ) {

        return ResponseEntity.ok(
                productService.getProductById(id)
        );
    }

    // UPDATE PRODUCT
    @PutMapping("/update/{id}")
    public ResponseEntity<Product> updateProduct(
            @PathVariable String id,
            @RequestBody Product product
    ) {

        return ResponseEntity.ok(
                productService.updateProduct(id, product)
        );
    }

    // DELETE PRODUCT
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteProduct(
            @PathVariable String id
    ) {

        productService.deleteProduct(id);

        return ResponseEntity.ok("Product deleted successfully");
    }
}