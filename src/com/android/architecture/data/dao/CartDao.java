package com.android.architecture.data.dao;

import java.util.List;

/**
 * Data Access Object for Cart operations.
 */
public interface CartDao {
    List<Object> getAllCarts();
    Object getCartById(String cartId);
    void insertCart(Object cart);
    void deleteCart(Object cart);
}
