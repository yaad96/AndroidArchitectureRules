package com.android.architecture.network;

import retrofit2.Call;
import java.util.List;

/**
 * API interface referenced by SearchViewModel's (intentionally rule-violating) network example.
 * Lives under stubs/ so it is on the compile path but not part of the rule-checked src/ fixture set.
 */
public interface ApiService {
    Call<List<Object>> search(String query);
}
