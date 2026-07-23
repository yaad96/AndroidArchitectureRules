package com.android.architecture.domain.usecase;

import com.android.architecture.repository.ReviewRepository;
import java.util.List;

/**
 * Use Case for retrieving the reviews of a product.
 */
public class GetReviewUseCase {
    private ReviewRepository reviewRepository;

    public GetReviewUseCase(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    public List<Object> fetchReviews(String productId) {
        if (productId == null || productId.isEmpty()) {
            throw new IllegalArgumentException("Product ID cannot be null or empty");
        }
        return reviewRepository.getReviewsForProduct(productId);
    }
}
