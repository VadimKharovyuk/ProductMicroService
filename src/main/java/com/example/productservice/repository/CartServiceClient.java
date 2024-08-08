package com.example.productservice.repository;

import com.example.productservice.dto.CartDto;
import com.example.productservice.dto.CartItemDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "CART-SERVICE")
public interface CartServiceClient {


    @GetMapping("/carts/{userId}")
    CartDto getCart(@PathVariable("userId") Long userId);


    @PostMapping("/carts/items")
    ResponseEntity<Void> addItemToCart(@RequestBody CartItemDto cartItemDto);


//    @PostMapping("/carts/items")
//    void addItemToCart(@RequestBody CartItemDto cartItemDto);

}
