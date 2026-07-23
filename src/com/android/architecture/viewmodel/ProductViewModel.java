package com.android.architecture.viewmodel;

import com.android.architecture.repository.ProductRepository;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import android.content.Context;
import java.util.List;

/**
 * ViewModel for Product-related UI data.
 */
public class ProductViewModel extends ViewModel {
    private ProductRepository productRepository;
    private Context context;
    private MutableLiveData<Object> productData = new MutableLiveData<>();
    private MutableLiveData<List<Object>> productListData = new MutableLiveData<>();

    public ProductViewModel(Context context) {
        this.productRepository = new ProductRepository();
        this.context = context;
    }

    public LiveData<Object> getProductData() {
        return productData;
    }

    public LiveData<List<Object>> getProductListData() {
        return productListData;
    }

    public void loadProduct(String productId) {
        Object product = productRepository.getProductById(productId);
        productData.setValue(product);
    }

    public void loadAllProducts() {
        List<Object> products = productRepository.getAllProducts();
        productListData.setValue(products);
    }
}
