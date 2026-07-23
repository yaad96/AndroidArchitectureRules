package retrofit2;

/** Compile-only stub of retrofit2.Call&lt;T&gt;. */
public interface Call<T> {
    void enqueue(Callback<T> callback);
}
