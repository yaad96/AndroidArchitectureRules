package com.android.architecture.viewmodel;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

/**
 * ViewModel for the product detail screen.
 */
public class ProductDetailViewModel extends ViewModel {
    private Object database;
    private MutableLiveData<Object> product = new MutableLiveData<>();

    public ProductDetailViewModel() {
        this.database = null;
    }

    public LiveData<Object> getProduct() {
        return product;
    }

    public void loadProduct(String productId) {
        product.setValue(database);
    }
}
