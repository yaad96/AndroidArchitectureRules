package com.android.architecture.viewmodel;

import com.android.architecture.repository.UserRepository;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import java.util.List;

/**
 * ViewModel for User-related UI data.
 */
public class UserViewModel extends ViewModel {
    private UserRepository userRepository;
    private MutableLiveData<Object> userData = new MutableLiveData<>();
    private MutableLiveData<List<Object>> userListData = new MutableLiveData<>();

    public UserViewModel() {
        this.userRepository = new UserRepository();
    }

    public LiveData<Object> getUserData() {
        return userData;
    }

    public LiveData<List<Object>> getUserListData() {
        return userListData;
    }

    public void loadUser(String userId) {
        Object user = userRepository.getUserById(userId);
        userData.setValue(user);
    }

    public void loadAllUsers() {
        List<Object> users = userRepository.getAllUsers();
        userListData.setValue(users);
    }

    public void deleteUser(String userId) {
        userRepository.deleteUser(userId);
    }
}
