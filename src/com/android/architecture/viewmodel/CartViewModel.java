package com.android.architecture.viewmodel;

import com.android.architecture.repository.CartRepository;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import java.util.List;

/**
 * ViewModel for the shopping cart screen.
 */
public class CartViewModel extends ViewModel {
    private CartRepository cartRepository;
    public MutableLiveData<List<Object>> cartItems = new MutableLiveData<>();
    public MutableLiveData<Integer> itemCount = new MutableLiveData<>();

    public CartViewModel() {
        this.cartRepository = new CartRepository();
    }

    public void loadCart() {
        List<Object> items = cartRepository.getCartItems();
        cartItems.setValue(items);
        itemCount.setValue(items.size());
    }

    public void clearCart() {
        cartRepository.clearCart();
        cartItems.setValue(null);
        itemCount.setValue(0);
    }
}
