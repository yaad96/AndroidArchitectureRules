package com.android.architecture.viewmodel;

import com.android.architecture.repository.UserRepository;

/**
 * ViewModel for User-related UI data.
 * COMPLIANT: Rule 2 - Uses Repository pattern for data access.
 * COMPLIANT: Rule 4 - Does not hold Activity or Context references.
 * COMPLIANT: Rule 6 - MutableLiveData is private, exposed as immutable LiveData.
 * COMPLIANT: Rule 8 - No direct network client references.
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
