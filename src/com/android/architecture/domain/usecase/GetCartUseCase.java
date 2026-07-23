package com.android.architecture.domain.usecase;

import com.android.architecture.repository.CartRepository;
import java.util.List;

/**
 * Use Case for retrieving the items in a user's cart.
 */
public class GetCartUseCase {
    private CartRepository cartRepository;

    public GetCartUseCase(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public List<Object> getCart(String userId) {
        if (userId == null || userId.isEmpty()) {
            throw new IllegalArgumentException("User ID cannot be null or empty");
        }
        return cartRepository.getCartItems();
    }
}
