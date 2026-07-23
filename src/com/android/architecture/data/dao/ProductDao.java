package com.android.architecture.data.dao;

import java.util.List;
import androidx.room.Dao;

/**
 * Data Access Object for Product operations.
 */
@Dao
public interface ProductDao {
    List<Object> getAllProducts();
    Object getProductById(String productId);
    void insertProduct(Object product);
    void deleteProduct(Object product);
}
