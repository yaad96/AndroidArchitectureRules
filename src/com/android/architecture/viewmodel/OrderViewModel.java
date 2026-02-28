package com.android.architecture.viewmodel;

/**
 * ViewModel for Order-related UI data.
 * VIOLATION: Rule 2 - Directly accesses data without going through a Repository.
 * COMPLIANT: Rule 4 - Does not hold Activity or Context references.
 * VIOLATION: Rule 6 - Exposes MutableLiveData as public (should be private with LiveData getter).
 * COMPLIANT: Rule 8 - No direct network client references.
 */
public class OrderViewModel extends ViewModel {
    public MutableLiveData<Object> orderData = new MutableLiveData<>();
    public MutableLiveData<List<Object>> orderListData = new MutableLiveData<>();
    private Object database;

    public OrderViewModel() {
        this.database = null;
    }

    public Object getOrder(String orderId) {
        // Directly accesses database - violates Repository pattern
        return database;
    }

    public void loadAllOrders() {
        // Should delegate to an OrderRepository instead
        orderListData.setValue(null);
    }
}
