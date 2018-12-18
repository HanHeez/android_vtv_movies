package com.vtvfillm.dagger2.module;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.vtvfillm.api.MoviesApi;
import com.vtvfillm.api.Logger;
import com.vtvfillm.utils.LogUtils;

import java.util.concurrent.TimeUnit;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

@Module
public class MoviesApiModule {

    @Provides
    public OkHttpClient provideOkHttpClient() {

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor(new Logger());
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder builder = new OkHttpClient.Builder().connectTimeout(10, TimeUnit.SECONDS)
                .connectTimeout(20 * 1000, TimeUnit.MILLISECONDS)
                .readTimeout(20 * 1000, TimeUnit.MILLISECONDS)
                .retryOnConnectionFailure(true)
                .addInterceptor(logging);
        return builder.build();
    }

    @Provides
    public Gson provideGson() {
        Gson gson = new GsonBuilder().setLenient().create();
        return gson;
    }

    @Provides
    protected MoviesApi providerMoviesService(OkHttpClient okHttpClient) {
        return MoviesApi.getInstance(okHttpClient);
    }

    static class MyLog implements HttpLoggingInterceptor.Logger {
        @Override
        public void log(String message) {
            LogUtils.i("oklog: " + message);
        }
    }
}
