package com.android.architecture.viewmodel;

import com.android.architecture.repository.UserRepository;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import android.app.Activity;

/**
 * ViewModel for the user profile screen.
 */
public class ProfileViewModel extends ViewModel {
    private UserRepository userRepository;
    private Activity activity;
    private MutableLiveData<Object> profileData = new MutableLiveData<>();

    public ProfileViewModel(Activity activity) {
        this.userRepository = new UserRepository();
        this.activity = activity;
    }

    public LiveData<Object> getProfileData() {
        return profileData;
    }

    public void loadProfile(String userId) {
        Object user = userRepository.getUserById(userId);
        profileData.setValue(user);
    }

    public void deleteProfile(String userId) {
        userRepository.deleteUser(userId);
    }
}
