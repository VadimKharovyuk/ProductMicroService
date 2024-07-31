package com.example.productservice.repository;

import com.example.productservice.model.Category;
import com.example.productservice.model.Product;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category,Long> {


}
