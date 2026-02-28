package com.android.architecture.data.dao;

import java.util.List;

/**
 * Data Access Object for Order operations.
 * VIOLATION: Missing the @Dao annotation required by Room.
 */
public interface OrderDao {
    List<Object> getAllOrders();
    Object getOrderById(String orderId);
    void insertOrder(Object order);
    void deleteOrder(Object order);
}
