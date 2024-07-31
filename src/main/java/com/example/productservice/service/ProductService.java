package com.example.productservice.service;

import com.example.productservice.model.Product;
import com.example.productservice.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;


    public Product save(Product product){
        return productRepository.save(product);
    }


    public List<Product> getAllProducts() {
        return productRepository.findAll();

    }
}
