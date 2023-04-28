package com.pofolio.web.development.project.NovaMarket.service;

import com.pofolio.web.development.project.NovaMarket.entity.Review;
import org.springframework.data.domain.Page;


public interface ReviewService {
    Page<Review> getAllReviewsByProductId(Long productId, int pageSize, int pageNumber);

    Review saveReview(Long productId,Review review);
}
