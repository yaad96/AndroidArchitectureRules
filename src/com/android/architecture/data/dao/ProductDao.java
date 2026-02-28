package com.android.architecture.data.dao;

import java.util.List;

/**
 * Data Access Object for Product operations.
 * COMPLIANT: Has the @Dao annotation required by Room.
 */
@Dao
public interface ProductDao {
    List<Object> getAllProducts();
    Object getProductById(String productId);
    void insertProduct(Object product);
    void deleteProduct(Object product);
}
