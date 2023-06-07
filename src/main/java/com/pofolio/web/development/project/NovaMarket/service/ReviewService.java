package com.pofolio.web.development.project.NovaMarket.service;

import com.pofolio.web.development.project.NovaMarket.entity.Review;
import com.pofolio.web.development.project.NovaMarket.requestModels.ReviewRequest;
import org.springframework.data.domain.Page;

import java.util.Optional;


public interface ReviewService {
    Page<Review> getAllReviewsByProductId(Long productId, int pageSize, int pageNumber);

    Review saveReview(Long customerId, ReviewRequest reviewRequest) throws Exception;

    Optional<Review> getReview(Long productId, Long customerId);
}
