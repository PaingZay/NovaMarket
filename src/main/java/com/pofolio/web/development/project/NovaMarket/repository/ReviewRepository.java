package com.pofolio.web.development.project.NovaMarket.repository;

import com.pofolio.web.development.project.NovaMarket.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    @Query("SELECT r FROM Review r where r.product.id = :productId")
    Page<Review> findReviewsByProductId(@Param("productId") Long productId, Pageable pageable);

    @Query("SELECT r FROM Review r where r.product.id = :productId and r.customer.id = :customerId")
    Optional<Review> findReviewByUserIdAndProductId(@Param("productId") Long productId, @Param("customerId") Long customerId);
}
