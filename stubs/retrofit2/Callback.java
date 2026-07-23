package retrofit2;

/** Compile-only stub of retrofit2.Callback&lt;T&gt;. */
public interface Callback<T> {
    void onResponse(Call<T> call, Response<T> response);
    void onFailure(Call<T> call, Throwable t);
}
