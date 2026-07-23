package okhttp3;

import java.util.concurrent.TimeUnit;

/** Compile-only stub of okhttp3.OkHttpClient (fluent Builder). */
public class OkHttpClient {
    public static class Builder {
        public Builder connectTimeout(long timeout, TimeUnit unit) { return this; }
        public Builder readTimeout(long timeout, TimeUnit unit) { return this; }
        public Builder writeTimeout(long timeout, TimeUnit unit) { return this; }
        public OkHttpClient build() { return new OkHttpClient(); }
    }
}
