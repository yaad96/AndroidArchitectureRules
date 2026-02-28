package com.android.architecture.viewmodel;

import com.android.architecture.repository.ProductRepository;
import java.util.List;
import java.util.ArrayList;

/**
 * ViewModel for Search functionality.
 * COMPLIANT: Rule 2 - Has a Repository reference.
 * COMPLIANT: Rule 4 - Does not hold Activity or Context references.
 * COMPLIANT: Rule 6 - MutableLiveData is private.
 * VIOLATION: Rule 8 - Contains Retrofit and OkHttpClient fields directly.
 *   ViewModels should not hold network clients. All network access should go
 *   through the Repository layer. To fix this, you must:
 *   (1) Move Retrofit and OkHttpClient into a dedicated ApiService or Repository class
 *   (2) Create proper API interface definitions
 *   (3) Inject the Repository instead of the network clients
 *   (4) Replace all direct network calls with Repository method calls
 *   (5) Handle error mapping in the Repository rather than the ViewModel
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
        // Direct network call in ViewModel - violates separation of concerns
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
        // This part correctly uses the repository
        List<Object> localResults = productRepository.getAllProducts();
        searchResults.setValue(localResults);
    }
}
