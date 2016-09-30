package ru.profit_group.scorocodesdk;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.profit_group.scorocodesdk.Objects.Doc;
import ru.profit_group.scorocodesdk.Objects.Query;
import ru.profit_group.scorocodesdk.Objects.Sort;
import ru.profit_group.scorocodesdk.Requests.RequestCount;
import ru.profit_group.scorocodesdk.Requests.RequestFind;
import ru.profit_group.scorocodesdk.Requests.RequestInsert;
import ru.profit_group.scorocodesdk.Requests.RequestLoginUser;
import ru.profit_group.scorocodesdk.Requests.RequestLogoutUser;
import ru.profit_group.scorocodesdk.Requests.RequestRegisterUser;
import ru.profit_group.scorocodesdk.Requests.RequestRemove;
import ru.profit_group.scorocodesdk.Requests.RequestStatistic;
import ru.profit_group.scorocodesdk.Requests.RequestUpdate;
import ru.profit_group.scorocodesdk.Requests.RequestUpdateById;
import ru.profit_group.scorocodesdk.Responses.ResponseCount;
import ru.profit_group.scorocodesdk.Responses.ResponseDefault;
import ru.profit_group.scorocodesdk.Responses.ResponseAppStatistic;
import ru.profit_group.scorocodesdk.Responses.ResponseFind;
import ru.profit_group.scorocodesdk.Responses.ResponseInsert;
import ru.profit_group.scorocodesdk.Responses.ResponseLogin;
import ru.profit_group.scorocodesdk.Responses.ResponseRemove;
import ru.profit_group.scorocodesdk.Responses.ResponseUpdate;
import ru.profit_group.scorocodesdk.Responses.ResponseUpdateById;

/**
 * Created by peter on 12/09/16.
 */
public class ScorocodeSdk {

    private static final String BASE_URL = "https://api.scorocode.ru";

    public static void getApplicationStatistic(
            @NonNull String appId,
            @NonNull String clientKey,
            @NonNull String accessKey,
            @NonNull Callback<ResponseAppStatistic> callback) throws IOException {

        Call<ResponseAppStatistic> appStatisticCall = getScorocodeApi().getAppStatistic(new RequestStatistic(appId, clientKey, accessKey));
        appStatisticCall.enqueue(callback);
    }

    public static void registerUser(
            @NonNull String appId,
            @NonNull String clientKey,
            @NonNull String userName,
            @NonNull String userEmail,
            @NonNull String userPassword,
            @Nullable HashMap<String, String>  doc,
            @NonNull Callback<ResponseDefault> callback) {

        Call<ResponseDefault> registerUserCall = getScorocodeApi().register(new RequestRegisterUser(appId, clientKey, userName, userEmail, userPassword, doc));
        registerUserCall.enqueue(callback);
    }

    public static void loginUser(
            @NonNull String appId,
            @NonNull String clientKet,
            @NonNull String email,
            @NonNull String password,
            @NonNull Callback<ResponseLogin> callback) {

        Call<ResponseLogin> loginUserCall = getScorocodeApi().login(new RequestLoginUser(appId, clientKet, email, password));
        loginUserCall.enqueue(callback);
    }

    public static void logoutUser(
            @NonNull String appId,
            @NonNull String clientKey,
            @NonNull String sessionId,
            @NonNull Callback<ResponseDefault> callback) {

        Call<ResponseDefault> logoutUserCall = getScorocodeApi().logout(new RequestLogoutUser(appId, clientKey, sessionId));
        logoutUserCall.enqueue(callback);
    }

    public static void insertDocument(
            @NonNull String appId,
            @NonNull String clientId,
            @Nullable String accsessKey,
            @NonNull String sessionId,
            @NonNull String collectionName,
            @Nullable HashMap<String, String> doc,
            @NonNull Callback<ResponseInsert> callback) {

        Call<ResponseInsert> insertCall = getScorocodeApi().insert(new RequestInsert(appId, clientId, accsessKey, sessionId, collectionName, doc));
        insertCall.enqueue(callback);
    }

    public static void removeDocument(
            @NonNull String appId,
            @NonNull String clientKey,
            @Nullable String accessKey,
            @NonNull String sessionId,
            @NonNull String collectionName,
            @Nullable Query query,
            @Nullable Integer limit,
            Callback<ResponseRemove> callback) {

        Call<ResponseRemove> removeCall = getScorocodeApi().remove(new RequestRemove(appId, clientKey, accessKey, sessionId, collectionName, query, limit));
        removeCall.enqueue(callback);
    }

    public static void updateDocument(
            @NonNull String appId,
            @NonNull String clientKey,
            @Nullable String accountKey,
            @NonNull String sessionId,
            @NonNull String collectionName,
            @Nullable Query query,
            @NonNull HashMap<String, String> doc,
            @Nullable Long limit,
            Callback<ResponseUpdate> callback) {

        Call<ResponseUpdate> updateCall = getScorocodeApi().update(new RequestUpdate(appId, clientKey, accountKey, sessionId, collectionName, query, doc, limit));
        updateCall.enqueue(callback);
    }

    public static void updateDocumentById(
            @NonNull String appId,
            @NonNull String clientKey,
            @Nullable String accountKey,
            @NonNull String sessionId,
            @NonNull String collectionName,
            @NonNull Query query,
            @NonNull HashMap<String, String> doc,
            Callback<ResponseUpdateById> callback) {

        Call<ResponseUpdateById> updateByIdCall = getScorocodeApi().updateById(new RequestUpdateById(appId, clientKey, accountKey, sessionId, collectionName, query, doc));
        updateByIdCall.enqueue(callback);
    }

    public static void findDocument(
            @NonNull String appId,
            @NonNull String clientKey,
            @Nullable String accessKey,
            @NonNull String sessionId,
            @NonNull String collectionName,
            @Nullable Query query,
            @Nullable Sort sort,
            @Nullable String[] fieldsNamesToFind,
            @Nullable Integer limit,
            @Nullable Integer skip,
            Callback<ResponseFind> callback) {

        Call<ResponseFind> findCall = getScorocodeApi().find(new RequestFind(appId, clientKey, accessKey, sessionId, collectionName, query, sort, fieldsNamesToFind, limit, skip));
        findCall.enqueue(callback);
    }

    public static void getDocumentsCount(
            @NonNull String appId,
            @NonNull String clientKey,
            @Nullable String accsessKey,
            @NonNull String sessionId,
            @NonNull String collectionName,
            @Nullable Query query,
            Callback<ResponseCount> callback) {

        Call<ResponseCount> callCount = getScorocodeApi().count(new RequestCount(appId, clientKey, accsessKey, sessionId, collectionName, query));
        callCount.enqueue(callback);
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
