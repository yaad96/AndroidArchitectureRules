package com.android.architecture.viewmodel;

import com.android.architecture.repository.UserRepository;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import java.net.HttpURLConnection;

/**
 * ViewModel for background profile synchronisation.
 */
public class SyncViewModel extends ViewModel {
    private UserRepository userRepository;
    private HttpURLConnection connection;
    private MutableLiveData<Boolean> syncComplete = new MutableLiveData<>();

    public SyncViewModel() {
        this.userRepository = new UserRepository();
    }

    public LiveData<Boolean> getSyncComplete() {
        return syncComplete;
    }

    public void syncProfile(String userId) {
        userRepository.getUserById(userId);
        syncComplete.setValue(true);
    }
}
