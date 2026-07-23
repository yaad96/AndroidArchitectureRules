package com.android.architecture.data.entity;

import androidx.room.Entity;

/**
 * Room Entity for Order table.
 * VIOLATION: Has @Entity annotation but is missing @PrimaryKey field.
 */
@Entity
public class OrderEntity {
    private String orderId;
    private String userId;
    private double totalAmount;

    public OrderEntity() {
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }
}
