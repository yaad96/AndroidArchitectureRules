package com.android.architecture.viewmodel;

import com.android.architecture.repository.ProductRepository;
import com.android.architecture.network.ApiService;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.converter.gson.GsonConverterFactory;
import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 * ViewModel for Search functionality.
 */
public class SearchViewModel extends ViewModel {
    private ProductRepository productRepository;
    private Retrofit retrofit;
    private OkHttpClient httpClient;
    private MutableLiveData<List<Object>> searchResults = new MutableLiveData<>();
    private MutableLiveData<Boolean> isLoading = new MutableLiveData<>();

    public SearchViewModel() {
        this.productRepository = new ProductRepository();
        this.httpClient = new OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build();
        this.retrofit = new Retrofit.Builder()
            .baseUrl("https://api.example.com/")
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    }

    public LiveData<List<Object>> getSearchResults() {
        return searchResults;
    }

    public LiveData<Boolean> getIsLoading() {
        return isLoading;
    }

    public void searchProducts(String query) {
        isLoading.setValue(true);
        ApiService service = retrofit.create(ApiService.class);
        Call<List<Object>> call = service.search(query);
        call.enqueue(new Callback<List<Object>>() {
            public void onResponse(Call<List<Object>> c, Response<List<Object>> response) {
                if (response.isSuccessful()) {
                    searchResults.postValue(response.body());
                } else {
                    searchResults.postValue(new ArrayList<>());
                }
                isLoading.postValue(false);
            }

            public void onFailure(Call<List<Object>> c, Throwable t) {
                searchResults.postValue(new ArrayList<>());
                isLoading.postValue(false);
            }
        });
    }

    public void searchLocal(String query) {
        List<Object> localResults = productRepository.getAllProducts();
        searchResults.setValue(localResults);
    }
}
