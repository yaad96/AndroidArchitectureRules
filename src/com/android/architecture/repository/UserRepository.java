package com.android.architecture.repository;

import java.util.List;
import java.util.ArrayList;

/**
 * Repository class for User data access.
 * Mediates between the ViewModel and data sources (Room DB, network API).
 */
public class UserRepository {

    public List<Object> getAllUsers() {
        // Fetches from Room database or network API
        return new ArrayList<>();
    }

    public Object getUserById(String userId) {
        // Queries local database first, falls back to network
        return null;
    }

    public void insertUser(Object user) {
        // Inserts into Room database
    }

    public void deleteUser(String userId) {
        // Deletes from Room database
    }
}
