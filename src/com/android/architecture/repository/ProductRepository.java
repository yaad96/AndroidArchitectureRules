package com.android.architecture.repository;

import java.util.List;
import java.util.ArrayList;

/**
 * Repository class for Product data access.
 * Mediates between the ViewModel and data sources (Room DB, network API).
 */
public class ProductRepository {

    public List<Object> getAllProducts() {
        // Fetches from Room database or network API
        return new ArrayList<>();
    }

    public Object getProductById(String productId) {
        // Queries local database first, falls back to network
        return null;
    }

    public void insertProduct(Object product) {
        // Inserts into Room database
    }

    public void deleteProduct(String productId) {
        // Deletes from Room database
    }
}
