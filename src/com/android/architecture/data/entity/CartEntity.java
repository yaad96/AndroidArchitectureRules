package com.android.architecture.data.entity;

import androidx.room.Entity;

/**
 * Room Entity for Cart table.
 */
@Entity
public class CartEntity {
    private String cartId;
    private String userId;
    private int itemCount;

    public CartEntity() {
    }

    public String getCartId() {
        return cartId;
    }

    public void setCartId(String cartId) {
        this.cartId = cartId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getItemCount() {
        return itemCount;
    }

    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }
}
