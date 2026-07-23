package com.android.architecture.data.dao;

import java.util.List;

/**
 * Data Access Object for Review operations.
 */
public interface ReviewDao {
    List<Object> getAllReviews();
    Object getReviewById(String reviewId);
    void insertReview(Object review);
    void deleteReview(Object review);
}
