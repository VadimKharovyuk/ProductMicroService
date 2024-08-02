// ProductController.java
package com.example.productservice.controller;

import com.example.productservice.exp.ProductNotFoundException;
import com.example.productservice.model.Category;
import com.example.productservice.model.Product;
import com.example.productservice.service.ProductService;
import jakarta.ws.rs.DELETE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }


    @PostMapping("/add")
    public Product save(@RequestBody Product product) {
        return productService.save(product);
    }

    @GetMapping("/category/{categoryId}")
    public List<Product> getProductsByCategory(@PathVariable Long categoryId) {
        return productService.getProductsByCategory(categoryId);
    }

    @GetMapping("/categories")
    public List<Category> getAllCategories() {
        return productService.getAllCategories();
    }

    @PostMapping("/categories/add")
    public Category saveCategory(@RequestBody Category category) {
        return productService.saveCategory(category);
    }
    @DeleteMapping("/product/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable long id) {
        try {
            productService.deleteProductById(id);
            return ResponseEntity.noContent().build(); // 204 No Content
        } catch (ProductNotFoundException e) {
            return ResponseEntity.notFound().build(); // 404 Not Found
        }
    }



}
