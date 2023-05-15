package com.pofolio.web.development.project.NovaMarket.controller;

import com.pofolio.web.development.project.NovaMarket.NovaMarketApplication;
import com.pofolio.web.development.project.NovaMarket.entity.Review;
import com.pofolio.web.development.project.NovaMarket.service.ReviewService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/api")
public class ReviewController {

    @Autowired
    ReviewService reviewService;

    private static final Logger logger = LoggerFactory.getLogger(NovaMarketApplication.class);

    @GetMapping("/product/{productId}/reviews")
    public ResponseEntity<Page<Review>> getAllReviewsByProductId(@PathVariable(value = "productId") Long productId,
                                                    int pageSize, int pageNumber) {
        try {
            Page<Review> reviews = reviewService.getAllReviewsByProductId(productId, pageSize, pageNumber);
            if (reviews.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(reviews, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Client get customerId and set to review object

//    @PostMapping("/product/{productId}/reviews")
//    public Review createComment(@PathVariable(value = "productId") Long productId,
//                                 @Valid @RequestBody Review review) {
//        return reviewService.saveReview(productId, review);
//    }

    @PostMapping("/product/{productId}/reviews")
    public ResponseEntity<Review> saveMember(@PathVariable(value = "productId") Long productId,
                                            @Valid @RequestBody Review review) {
        logger.info("Creating new order");
        try {
            Review savedReview = reviewService.saveReview(productId, review);
            return new ResponseEntity<>(savedReview, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//
//    @PutMapping("/posts/{postId}/comments/{commentId}")
//    public Comment updateComment(@PathVariable(value = "postId") Long postId,
//                                 @PathVariable(value = "commentId") Long commentId,
//                                 @Valid @RequestBody Comment commentRequest) {
//        return commentService.updateComment(postId, commentId, commentRequest);
//    }
//
//    @DeleteMapping("/posts/{postId}/comments/{commentId}")
//    public ResponseEntity<?> deleteComment(@PathVariable(value = "postId") Long postId,
//                                           @PathVariable(value = "commentId") Long commentId) {
//
//        Boolean isDeleted = commentService.deleteComment(postId, commentId);
//
//        if (isDeleted) {
//            return ResponseEntity.ok().build();
//        } else {
//            throw new ResourceNotFoundException(
//                    "Comment not found with id " + commentId + " and postId " + postId);
//        }
//    }
}
