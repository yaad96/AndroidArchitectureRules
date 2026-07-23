package com.android.architecture.repository;

import java.util.List;
import java.util.ArrayList;

/**
 * Repository class for Review data access.
 * Mediates between the ViewModel and data sources (Room DB, network API).
 */
public class ReviewRepository {

    public List<Object> getReviewsForProduct(String productId) {
        // Fetches from Room database or network API
        return new ArrayList<>();
    }

    public double getAverageRating(String productId) {
        // Aggregates the stored ratings for a product
        return 0.0;
    }

    public void insertReview(Object review) {
        // Inserts into Room database
    }

    public void deleteReview(String reviewId) {
        // Deletes from Room database
    }
}
