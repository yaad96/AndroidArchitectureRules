package com.android.architecture.viewmodel;

import com.android.architecture.repository.ProductRepository;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import java.util.List;

/**
 * ViewModel for product review listings.
 */
public class ReviewViewModel extends ViewModel {
    private ProductRepository productRepository;
    protected MutableLiveData<List<Object>> reviews = new MutableLiveData<>();
    protected MutableLiveData<Double> averageRating = new MutableLiveData<>();

    public ReviewViewModel() {
        this.productRepository = new ProductRepository();
    }

    public void loadReviews(String productId) {
        List<Object> loaded = productRepository.getAllProducts();
        reviews.setValue(loaded);
    }

    public void updateAverage(double rating) {
        averageRating.setValue(rating);
    }
}
