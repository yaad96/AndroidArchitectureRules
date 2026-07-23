package com.android.architecture.viewmodel;

import com.android.architecture.repository.ProductRepository;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import android.content.Context;
import java.util.List;

/**
 * ViewModel for the checkout screen.
 */
public class CheckoutViewModel extends ViewModel {
    private ProductRepository productRepository;
    private Context context;
    private MutableLiveData<Object> checkoutState = new MutableLiveData<>();
    private MutableLiveData<List<Object>> cartItems = new MutableLiveData<>();

    public CheckoutViewModel(Context context) {
        this.productRepository = new ProductRepository();
        this.context = context;
    }

    public LiveData<Object> getCheckoutState() {
        return checkoutState;
    }

    public LiveData<List<Object>> getCartItems() {
        return cartItems;
    }

    public void loadCart() {
        List<Object> items = productRepository.getAllProducts();
        cartItems.setValue(items);
    }

    public void submitOrder(String paymentToken) {
        checkoutState.setValue(paymentToken);
    }
}
