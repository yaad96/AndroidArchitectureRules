package com.android.architecture.repository;

import java.util.List;
import java.util.ArrayList;

/**
 * Repository class for Notification data access.
 * Mediates between the ViewModel and data sources (Room DB, network API).
 */
public class NotificationRepository {

    public List<Object> getNotifications(String userId) {
        // Fetches from Room database or network API
        return new ArrayList<>();
    }

    public int getUnreadCount(String userId) {
        // Counts unread rows in the local database
        return 0;
    }

    public void markAllRead(String userId) {
        // Updates the local database
    }
}
