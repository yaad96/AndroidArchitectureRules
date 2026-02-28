package com.android.architecture.data.dao;

import java.util.List;

/**
 * Data Access Object for User operations.
 * COMPLIANT: Has the @Dao annotation required by Room.
 */
@Dao
public interface UserDao {
    List<Object> getAllUsers();
    Object getUserById(String userId);
    void insertUser(Object user);
    void deleteUser(Object user);
}
