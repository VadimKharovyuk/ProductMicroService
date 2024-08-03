// ProductController.java
package com.example.productservice.controller;

import com.example.productservice.exp.ProductNotFoundException;
import com.example.productservice.kafka.KafkaProducerConfig;
import com.example.productservice.kafka.kafkaProduser;
import com.example.productservice.model.Category;
import com.example.productservice.model.Product;
import com.example.productservice.service.ProductService;
import jakarta.ws.rs.DELETE;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final kafkaProduser produser;

@GetMapping("/test")
public  String test ( @RequestBody String  text){
   produser.sendProductUpdate(text);
   return "susses";
}
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

    @PostMapping("/delete/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable long id) {
        try {
            productService.deleteProductById(id);
            return ResponseEntity.noContent().build(); // 204 No Content
        } catch (ProductNotFoundException e) {
            return ResponseEntity.notFound().build(); // 404 Not Found
        }
    }


}
