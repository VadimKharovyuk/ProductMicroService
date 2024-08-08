package com.example.productservice.service;

import com.example.productservice.dto.CartDto;
import com.example.productservice.dto.CartItemDto;
import com.example.productservice.model.Product;
import com.example.productservice.repository.CartServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService {

    @Autowired
    private ProductService productService;

    @Autowired
    private CartServiceClient cartServiceClient;

    public void addProductToCart(Long productId, Integer quantity, Long userId) {
        // Найдите продукт по его ID
        Product product = productService.findProductById(productId);

        // Создайте DTO для CartItem
        CartItemDto cartItemDto = new CartItemDto();
        cartItemDto.setProductId(product.getId());
        cartItemDto.setProductName(product.getName());
        cartItemDto.setProductPrice(product.getPrice());
        cartItemDto.setQuantity(quantity);
        cartItemDto.setUserId(userId);

        // Используйте FeignClient для добавления товара в корзину
        cartServiceClient.addItemToCart(cartItemDto);
    }

    public CartDto getCart(Long userId) {
        return cartServiceClient.getCart(userId);
    }
}
