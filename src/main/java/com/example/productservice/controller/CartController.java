package com.example.productservice.controller;

import com.example.productservice.dto.CartDto;
import com.example.productservice.dto.CartItemDto;
import com.example.productservice.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping("/add-product")
    public ResponseEntity<Void> addProductToCart(@RequestBody CartItemDto cartItemDto) {
        try {
            cartService.addProductToCart(cartItemDto.getProductId(), cartItemDto.getQuantity(), cartItemDto.getUserId());
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            // Логирование и возврат ошибки
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("/{userId}")
    public ResponseEntity<CartDto> getCart(@PathVariable("userId") Long userId) {
        try {
            CartDto cartDto = cartService.getCart(userId);
            return new ResponseEntity<>(cartDto, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

}
