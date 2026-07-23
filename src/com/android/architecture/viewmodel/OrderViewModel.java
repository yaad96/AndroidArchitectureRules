package com.android.architecture.viewmodel;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import java.util.List;

/**
 * ViewModel for Order-related UI data.
 */
public class OrderViewModel extends ViewModel {
    public MutableLiveData<Object> orderData = new MutableLiveData<>();
    public MutableLiveData<List<Object>> orderListData = new MutableLiveData<>();
    private Object database;

    public OrderViewModel() {
        this.database = null;
    }

    public Object getOrder(String orderId) {
        return database;
    }

    public void loadAllOrders() {
        orderListData.setValue(null);
    }
}
