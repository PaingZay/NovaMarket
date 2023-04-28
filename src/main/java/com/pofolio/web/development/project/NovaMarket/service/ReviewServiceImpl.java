package com.pofolio.web.development.project.NovaMarket.service;

import com.pofolio.web.development.project.NovaMarket.Exception.ResourceNotFoundException;
import com.pofolio.web.development.project.NovaMarket.entity.Review;
import com.pofolio.web.development.project.NovaMarket.repository.ProductRepository;
import com.pofolio.web.development.project.NovaMarket.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ReviewServiceImpl implements ReviewService{

    @Autowired
    ReviewRepository reviewRepository;

    @Autowired
    ProductRepository productRepository;

    @Override
    public Page<Review> getAllReviewsByProductId(Long productId, int pageSize, int pageNumber) {
        Pageable page = PageRequest.of(pageNumber,pageSize);
        return reviewRepository.findReviewsByProductId(productId,page);
    }

    @Override
    public Review saveReview(Long productId, Review review) {
        return productRepository.findById(productId).map(product -> {
            review.setProduct(product);
            return reviewRepository.save(review);
        }).orElseThrow(() -> new ResourceNotFoundException("PostId " + productId + " not found"));
    }
}
