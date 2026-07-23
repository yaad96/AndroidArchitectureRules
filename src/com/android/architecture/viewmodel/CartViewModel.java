package com.android.architecture.viewmodel;

import com.android.architecture.repository.ProductRepository;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import java.util.List;

/**
 * ViewModel for the shopping cart screen.
 */
public class CartViewModel extends ViewModel {
    private ProductRepository productRepository;
    public MutableLiveData<List<Object>> cartItems = new MutableLiveData<>();
    public MutableLiveData<Integer> itemCount = new MutableLiveData<>();

    public CartViewModel() {
        this.productRepository = new ProductRepository();
    }

    public void loadCart() {
        List<Object> items = productRepository.getAllProducts();
        cartItems.setValue(items);
        itemCount.setValue(items.size());
    }

    public void clearCart() {
        cartItems.setValue(null);
        itemCount.setValue(0);
    }
}
