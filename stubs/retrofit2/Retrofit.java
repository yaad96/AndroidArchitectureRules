package retrofit2;

import okhttp3.OkHttpClient;

/** Compile-only stub of retrofit2.Retrofit (fluent Builder + create()). */
public class Retrofit {
    public <T> T create(Class<T> service) { return null; }

    public static class Builder {
        public Builder baseUrl(String baseUrl) { return this; }
        public Builder client(OkHttpClient client) { return this; }
        public Builder addConverterFactory(Object factory) { return this; }
        public Retrofit build() { return new Retrofit(); }
    }
}
