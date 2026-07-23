package com.android.architecture.dto;

/**
 * Data Transfer Object for Order data.
 * Used for Gson/Retrofit serialization and deserialization.
 */
public class OrderDto {
    private String orderId;
    private String userId;
    private double totalAmount;

    public OrderDto(String orderId, String userId, double totalAmount) {
        this.orderId = orderId;
        this.userId = userId;
        this.totalAmount = totalAmount;
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
