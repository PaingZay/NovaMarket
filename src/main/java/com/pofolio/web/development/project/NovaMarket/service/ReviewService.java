package com.pofolio.web.development.project.NovaMarket.service;

import com.pofolio.web.development.project.NovaMarket.entity.Review;
import org.springframework.data.domain.Page;

import java.util.Optional;


public interface ReviewService {
    Page<Review> getAllReviewsByProductId(Long productId, int pageSize, int pageNumber);

    Review saveReview(Long productId,Review review);

    Optional<Review> getReview(Long productId, Long customerId);
}
