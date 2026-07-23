package com.android.architecture.data.entity;

import androidx.room.Entity;

/**
 * Room Entity for Review table.
 * VIOLATION: Has @Entity annotation but is missing @PrimaryKey field.
 */
@Entity
public class ReviewEntity {
    private String reviewId;
    private String productId;
    private int rating;
    private String comment;

    public ReviewEntity() {
    }

    public String getReviewId() {
        return reviewId;
    }

    public void setReviewId(String reviewId) {
        this.reviewId = reviewId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
