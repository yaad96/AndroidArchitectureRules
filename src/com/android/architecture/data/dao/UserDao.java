package com.android.architecture.data.dao;

import java.util.List;
import androidx.room.Dao;

/**
 * Data Access Object for User operations.
 */
@Dao
public interface UserDao {
    List<Object> getAllUsers();
    Object getUserById(String userId);
    void insertUser(Object user);
    void deleteUser(Object user);
}
