
package com.example.productservice.service;
import com.example.productservice.dto.CartItemDto;
import com.example.productservice.dto.ReviewDTO;
import com.example.productservice.model.Category;
import com.example.productservice.model.Product;
import com.example.productservice.repository.CartServiceClient;
import com.example.productservice.repository.CategoryRepository;
import com.example.productservice.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.apache.kafka.common.errors.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private  final CartServiceClient cartFeignClient;


    public Product save(Product product) {
        return productRepository.save(product);
    }


    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public List<Product> getProductsByCategory(Long categoryId) {
        return productRepository.findByCategoryId(categoryId);
    }

    public Category saveCategory(Category category) {
        return categoryRepository.save(category);
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public void deleteProductById(Long id) {
        productRepository.deleteById(id);
    }

    public void deleteCategoryById(Long id) {
        categoryRepository.deleteById(id);
    }

    public List<Product> searchProductsByName(String name) {
        return productRepository.findByNameContainingIgnoreCase(name);
    }

    public Product findProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));
    }

    public void addProductToCart(Long userId, Long productId, String productName, int quantity) {
        CartItemDto cartItemDto = new CartItemDto();
        cartItemDto.setUserId(userId);
        cartItemDto.setProductId(productId);
        cartItemDto.setProductName(productName);
        cartItemDto.setQuantity(quantity);

        cartFeignClient.addItemToCart(cartItemDto);
    }

}