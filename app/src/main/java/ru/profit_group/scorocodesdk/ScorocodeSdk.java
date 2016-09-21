package ru.profit_group.scorocodesdk;

import android.support.annotation.NonNull;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.profit_group.scorocodesdk.InboundRequests.RequestStatistic;
import ru.profit_group.scorocodesdk.pojo_statistic.AppStatistic;

/**
 * Created by peter on 12/09/16.
 */
public class ScorocodeSdk {

    private static final String BASE_URL = "https://api.scorocode.ru";

    public static AppStatistic getApplicationStatistic(String appId, String clientKey, String accessKey) throws IOException {
        Call<AppStatistic> appStatisticCall = getScorocodeApi().getAppStatistic(new RequestStatistic(appId, clientKey, accessKey));
        return appStatisticCall.execute().body();
    }

    public static void getApplicationStatistic(String appId, String clientKey, String accessKey, Callback<AppStatistic> callback) throws IOException {
        Call<AppStatistic> appStatisticCall = getScorocodeApi().getAppStatistic(new RequestStatistic(appId, clientKey, accessKey));

        if(callback != null) {
            appStatisticCall.enqueue(callback);
        }
    }




    private static ScorocodeApi getScorocodeApi() {
        return getRetrofit().create(ScorocodeApi.class);
    }

    @NonNull
    private static Retrofit getRetrofit() {
        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        clientBuilder.addInterceptor(loggingInterceptor);

        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(clientBuilder.build())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

}
