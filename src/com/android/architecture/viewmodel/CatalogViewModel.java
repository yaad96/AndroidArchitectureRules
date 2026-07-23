package com.android.architecture.viewmodel;

import com.android.architecture.repository.ProductRepository;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import okhttp3.OkHttpClient;
import java.util.List;

/**
 * ViewModel for the product catalog screen.
 */
public class CatalogViewModel extends ViewModel {
    private ProductRepository productRepository;
    private OkHttpClient httpClient = new OkHttpClient();
    private MutableLiveData<List<Object>> catalog = new MutableLiveData<>();

    public CatalogViewModel() {
        this.productRepository = new ProductRepository();
    }

    public LiveData<List<Object>> getCatalog() {
        return catalog;
    }

    public void loadCatalog() {
        catalog.setValue(productRepository.getAllProducts());
    }
}
