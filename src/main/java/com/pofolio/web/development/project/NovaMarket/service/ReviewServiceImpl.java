package com.pofolio.web.development.project.NovaMarket.service;

import com.pofolio.web.development.project.NovaMarket.entity.Review;
import com.pofolio.web.development.project.NovaMarket.repository.ProductRepository;
import com.pofolio.web.development.project.NovaMarket.repository.ReviewRepository;
import com.pofolio.web.development.project.NovaMarket.repository.UserRepository;
import com.pofolio.web.development.project.NovaMarket.requestModels.ReviewRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService{

    @Autowired
    ReviewRepository reviewRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public Page<Review> getAllReviewsByProductId(Long productId, int pageSize, int pageNumber) {
        Pageable page = PageRequest.of(pageNumber,pageSize);
        return reviewRepository.findReviewsByProductId(productId,page);
    }

    @Override
    public Review saveReview(Long customerId, ReviewRequest reviewRequest) throws Exception {

        Optional<Review> existedReview = reviewRepository.findReviewByUserIdAndProductId(customerId, reviewRequest.getProductId());
        if(existedReview.isPresent()) {
            throw new Exception("Review already existed");
        }

        Review review = new Review();
        review.setProduct(productRepository.findById(reviewRequest.getProductId()).get());
        review.setCustomer(userRepository.findById(customerId).get());
        review.setReviewRating(reviewRequest.getReviewRating());
        if(reviewRequest.getReviewText().isPresent()) {
            review.setReviewText(reviewRequest.getReviewText().map(Object::toString).orElse(null));
        }
        review.setReviewDate(LocalDate.now());
        return reviewRepository.save(review);
    }

    @Override
    public Optional<Review> getReview(Long productId, Long customerId) {
        return reviewRepository.findReviewByUserIdAndProductId(productId, customerId);
    }
}
