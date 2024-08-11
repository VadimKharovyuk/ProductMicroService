package com.example.productservice.service;

import com.example.productservice.Mapper.ReviewMapper;
import com.example.productservice.dto.ReviewDTO;
import com.example.productservice.model.Review;
import com.example.productservice.repository.ReviewRepository;
import com.example.productservice.repository.ReviewService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ReviewServiceImpl implements ReviewService {


    private  final ReviewRepository reviewRepository;

    @Override
    public Review saveReview(Review review) {
        return reviewRepository.save(review);
    }

    @Override
    public Optional<Review> getReviewById(Long id) {
        return reviewRepository.findById(id);
    }

    @Override
    public void deleteReview(Long id) {
        reviewRepository.deleteById(id);
    }
    public List<ReviewDTO> getReviewsByProductId(Long productId) {
        List<Review> reviews = reviewRepository.findByProductId(productId);
        return reviews.stream()
                .map(ReviewMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteReviewsByProductId(Long productId) {
        List<Review> reviews = reviewRepository.findByProductId(productId);
        reviewRepository.deleteAll(reviews);
    }

}
