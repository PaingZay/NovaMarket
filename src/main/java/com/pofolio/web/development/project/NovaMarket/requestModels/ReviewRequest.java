package com.pofolio.web.development.project.NovaMarket.requestModels;

import lombok.Data;

import java.util.Optional;


@Data
public class ReviewRequest {
    private double reviewRating;
    private Long productId;
    private Optional<String> reviewText;
}
