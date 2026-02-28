package com.android.architecture.domain.usecase;

import com.android.architecture.repository.UserRepository;
import java.util.List;

/**
 * Use Case for retrieving a user by ID.
 * COMPLIANT: Rule 7 - Has a single execute() method as the entry point.
 */
public class GetUserUseCase {
    private UserRepository userRepository;

    public GetUserUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Object execute(String userId) {
        if (userId == null || userId.isEmpty()) {
            throw new IllegalArgumentException("User ID cannot be null or empty");
        }
        Object user = userRepository.getUserById(userId);
        if (user == null) {
            throw new RuntimeException("User not found: " + userId);
        }
        return user;
    }
}
