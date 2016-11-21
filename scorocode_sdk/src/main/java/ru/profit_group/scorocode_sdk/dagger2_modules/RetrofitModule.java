package ru.profit_group.scorocode_sdk.dagger2_modules;

import javax.inject.Scope;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.profit_group.scorocode_sdk.dagger2_scopes.SingletonScope;

/**
 * Created by Peter Staranchuk on 11/21/16
 */

@Module
public class RetrofitModule {
    private static final String BASE_URL = "https://api.scorocode.ru";

    @Provides
    @SingletonScope
    public Retrofit retrofit(OkHttpClient client, RxJavaCallAdapterFactory rxJavaCallAdapterFactory, GsonConverterFactory gsonConverterFactory) {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addCallAdapterFactory(rxJavaCallAdapterFactory)
                .addConverterFactory(gsonConverterFactory)
                .build();
    }

    @Provides
    @SingletonScope
    public OkHttpClient okHttpClient(HttpLoggingInterceptor loggingInterceptor) {
        return new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .followRedirects(false)
                .build();
    }

    @Provides
    @SingletonScope
    public HttpLoggingInterceptor loggingInterceptor() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.NONE);

        return loggingInterceptor;
    }

    @Provides
    @SingletonScope
    public RxJavaCallAdapterFactory rxJavaCallAdapterFactory() {
        return RxJavaCallAdapterFactory.create();
    }

    @Provides
    @SingletonScope
    public GsonConverterFactory gsonConverterFactory() {
        return GsonConverterFactory.create();
    }
}
