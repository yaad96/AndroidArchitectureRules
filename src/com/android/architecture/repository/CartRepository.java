package com.android.architecture.repository;

import java.util.List;
import java.util.ArrayList;

/**
 * Repository class for Cart data access.
 * Mediates between the ViewModel and data sources (Room DB, network API).
 */
public class CartRepository {

    public List<Object> getCartItems() {
        // Fetches from Room database or network API
        return new ArrayList<>();
    }

    public void addItem(Object item) {
        // Inserts into Room database
    }

    public void removeItem(String itemId) {
        // Deletes from Room database
    }

    public void clearCart() {
        // Empties the cart table
    }
}
