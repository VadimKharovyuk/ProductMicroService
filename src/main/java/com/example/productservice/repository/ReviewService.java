package com.example.productservice.repository;

import com.example.productservice.dto.ReviewDTO;
import com.example.productservice.model.Review;

import java.util.List;
import java.util.Optional;

public interface ReviewService {

    Review saveReview(Review review);


    Optional<Review> getReviewById(Long id);

    void deleteReview(Long id);

    List<ReviewDTO> getReviewsByProductId(Long productId);


    void deleteReviewsByProductId(Long productId);

}
