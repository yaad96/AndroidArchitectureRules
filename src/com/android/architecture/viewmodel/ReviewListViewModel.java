package com.android.architecture.viewmodel;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import java.util.List;

/**
 * ViewModel for the review list screen.
 */
public class ReviewListViewModel extends ViewModel {
    private Object database;
    private MutableLiveData<List<Object>> reviews = new MutableLiveData<>();
    private MutableLiveData<Double> averageRating = new MutableLiveData<>();

    public ReviewListViewModel() {
        this.database = null;
    }

    public LiveData<List<Object>> getReviews() {
        return reviews;
    }

    public LiveData<Double> getAverageRating() {
        return averageRating;
    }

    public void loadReviews(String productId) {
        reviews.setValue(null);
        averageRating.setValue(0.0);
    }
}
