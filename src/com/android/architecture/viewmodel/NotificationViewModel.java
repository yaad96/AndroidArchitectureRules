package com.android.architecture.viewmodel;

import com.android.architecture.repository.NotificationRepository;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import java.util.List;

/**
 * ViewModel for the notification centre.
 */
public class NotificationViewModel extends ViewModel {
    private NotificationRepository notificationRepository;
    public MutableLiveData<List<Object>> notifications = new MutableLiveData<>();
    protected MutableLiveData<Integer> unreadCount = new MutableLiveData<>();

    public NotificationViewModel() {
        this.notificationRepository = new NotificationRepository();
    }

    public void loadNotifications(String userId) {
        notifications.setValue(notificationRepository.getNotifications(userId));
        unreadCount.setValue(notificationRepository.getUnreadCount(userId));
    }

    public void markAllRead(String userId) {
        notificationRepository.markAllRead(userId);
        unreadCount.setValue(0);
    }
}
