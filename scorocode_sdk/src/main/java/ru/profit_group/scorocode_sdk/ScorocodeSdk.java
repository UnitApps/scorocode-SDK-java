package ru.profit_group.scorocode_sdk;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.profit_group.scorocode_sdk.Requests.messages.MessageEmail;
import ru.profit_group.scorocode_sdk.Requests.messages.MessagePush;
import ru.profit_group.scorocode_sdk.Requests.messages.MessageSms;
import ru.profit_group.scorocode_sdk.scorocode_objects.Query;
import ru.profit_group.scorocode_sdk.scorocode_objects.Sort;
import ru.profit_group.scorocode_sdk.Requests.data.RequestCount;
import ru.profit_group.scorocode_sdk.Requests.data.RequestFind;
import ru.profit_group.scorocode_sdk.Requests.data.RequestInsert;
import ru.profit_group.scorocode_sdk.Requests.files.RequestFile;
import ru.profit_group.scorocode_sdk.Requests.files.RequestUpload;
import ru.profit_group.scorocode_sdk.Requests.messages.RequestSendEmail;
import ru.profit_group.scorocode_sdk.Requests.messages.RequestSendPush;
import ru.profit_group.scorocode_sdk.Requests.messages.RequestSendSms;
import ru.profit_group.scorocode_sdk.Requests.scripts.RequestSendScriptTask;
import ru.profit_group.scorocode_sdk.Requests.user.RequestLoginUser;
import ru.profit_group.scorocode_sdk.Requests.user.RequestLogoutUser;
import ru.profit_group.scorocode_sdk.Requests.user.RequestRegisterUser;
import ru.profit_group.scorocode_sdk.Requests.data.RequestRemove;
import ru.profit_group.scorocode_sdk.Requests.statistic.RequestStatistic;
import ru.profit_group.scorocode_sdk.Requests.data.RequestUpdate;
import ru.profit_group.scorocode_sdk.Requests.data.RequestUpdateById;
import ru.profit_group.scorocode_sdk.Responses.ResponseString;
import ru.profit_group.scorocode_sdk.Responses.data.ResponseCount;
import ru.profit_group.scorocode_sdk.Responses.ResponseCodes;
import ru.profit_group.scorocode_sdk.Responses.statistic.ResponseAppStatistic;
import ru.profit_group.scorocode_sdk.Responses.data.ResponseInsert;
import ru.profit_group.scorocode_sdk.Responses.user.ResponseLogin;
import ru.profit_group.scorocode_sdk.Responses.data.ResponseRemove;
import ru.profit_group.scorocode_sdk.Responses.data.ResponseUpdate;
import ru.profit_group.scorocode_sdk.Responses.data.ResponseUpdateById;

/**
 * Created by Peter Staranchuk on 5/10/16
 */
public class ScorocodeSdk {

    private static final String BASE_URL = "https://api.scorocode.ru";
    private static String _applicationId;
    private static String _clientKey;
    private static String _masterKey;
    private static String _fileKey;
    private static String _messageKey;
    private static String _sessionId;

    public static void initWith(
            @NonNull String applicationId,
            @NonNull String clientKey,
            @Nullable String masterKey,
            @Nullable String fileKey,
            @Nullable String messageKey) {

        _applicationId = applicationId;
        _clientKey = clientKey;
        _masterKey = masterKey;
        _fileKey = fileKey;
        _messageKey = messageKey;
    }

    public static String getAppId() throws IllegalStateException {
        if(_applicationId == null) {
            throw new IllegalStateException("you must initialize SDK first. Please use initWith(keys..) method.");
        }
        return _applicationId;
    }

    public static String getClientKey() throws IllegalStateException {
        if(_clientKey == null) {
            throw new IllegalStateException("you must initialize SDK first. Please use initWith(keys..) method.");
        }
        return _clientKey;
    }

    public static String getSessionId() throws IllegalStateException {
        return _sessionId;
    }

    public static String getMasterKey() {
        return _masterKey;
    }

    public static String getFileKey() {
        return _fileKey;
    }

    public static String getMasterOrFileKey() {
        if(_masterKey != null) {
            return _masterKey;
        } else {
            return _fileKey;
        }
    }

    public static String getMasterOrMessageKey() {
        if(_masterKey != null) {
            return _masterKey;
        } else {
            return _messageKey;
        }
    }

    public static String getMessageKey() {
        return _messageKey;
    }

    public static void setSessionId(String _sessionId) {
        ScorocodeSdk._sessionId = _sessionId;
    }

    public static void getApplicationStatistic(
            @NonNull Callback<ResponseAppStatistic> callback) throws IOException {

        Call<ResponseAppStatistic> appStatisticCall = getScorocodeApi().getAppStatistic(new RequestStatistic(getAppId(), getClientKey(), getMasterKey()));
        appStatisticCall.enqueue(callback);
    }

    public static void registerUser(
            @NonNull String userName,
            @NonNull String userEmail,
            @NonNull String userPassword,
            @Nullable HashMap<String, String>  doc,
            @NonNull Callback<ResponseCodes> callback) {

        Call<ResponseCodes> registerUserCall = getScorocodeApi().register(new RequestRegisterUser(getAppId(), getClientKey(), userName, userEmail, userPassword, doc));
        registerUserCall.enqueue(callback);
    }

    public static void loginUser(
            @NonNull String email,
            @NonNull String password,
            @NonNull Callback<ResponseLogin> callback) {

        Call<ResponseLogin> loginUserCall = getScorocodeApi().login(new RequestLoginUser(getAppId(), getClientKey(), email, password));
        loginUserCall.enqueue(callback);
    }

    public static void logoutUser(
            @NonNull Callback<ResponseCodes> callback) {

        Call<ResponseCodes> logoutUserCall = getScorocodeApi().logout(new RequestLogoutUser(getAppId(), getClientKey(), getSessionId()));
        logoutUserCall.enqueue(callback);
    }

    public static void insertDocument(
            @NonNull String collectionName,
            @Nullable HashMap<String, String> doc,
            @NonNull Callback<ResponseInsert> callback) {

        Call<ResponseInsert> insertCall = getScorocodeApi().insert(new RequestInsert(getAppId(), getClientKey(), getMasterKey(), getSessionId(), collectionName, doc));
        insertCall.enqueue(callback);
    }

    public static void removeDocument(
            @NonNull String collectionName,
            @Nullable Query query,
            @Nullable Integer limit,
            Callback<ResponseRemove> callback) {

        Call<ResponseRemove> removeCall = getScorocodeApi().remove(new RequestRemove(getAppId(), getClientKey(), getMasterKey(), getSessionId(), collectionName, query, limit));
        removeCall.enqueue(callback);
    }

    public static void updateDocument(
            @NonNull String collectionName,
            @Nullable Query query,
            @NonNull HashMap<String, HashMap<String, Object>> doc,
            @Nullable Integer limit,
            Callback<ResponseUpdate> callback) {

        Call<ResponseUpdate> updateCall = getScorocodeApi().update(new RequestUpdate(getAppId(), getClientKey(), getMasterKey(), getSessionId(), collectionName, query, doc, limit));
        updateCall.enqueue(callback);
    }

    public static void updateDocumentById(
            @NonNull String collectionName,
            @NonNull HashMap<String, String> query,
            @NonNull HashMap<String, HashMap<String,Object>> doc,
            Callback<ResponseUpdateById> callback) {

        Call<ResponseUpdateById> updateByIdCall = getScorocodeApi().updateById(new RequestUpdateById(getAppId(), getClientKey(), getMasterKey(), getSessionId(), collectionName, query, doc));
        updateByIdCall.enqueue(callback);
    }

    public static void findDocument(
            @NonNull String collectionName,
            @Nullable Query query,
            @Nullable Sort sort,
            @Nullable List<String> fieldsNamesToFind,
            @Nullable Integer limit,
            @Nullable Integer skip,
            Callback<ResponseString> callback) {

        Call<ResponseString> findCall = getScorocodeApi().find(new RequestFind(getAppId(), getClientKey(), getMasterKey(), getSessionId(), collectionName, query, sort, fieldsNamesToFind, limit, skip));
        findCall.enqueue(callback);
    }

    public static void getDocumentsCount(
            @NonNull String collectionName,
            @Nullable Query query,
            Callback<ResponseCount> callback) {

        Call<ResponseCount> callCount = getScorocodeApi().count(new RequestCount(getAppId(), getClientKey(), getMasterKey(), getSessionId(), collectionName, query));
        callCount.enqueue(callback);
    }

    public static void uploadFile(
            @NonNull String collectionName,
            @NonNull String documentId,
            @NonNull String fieldName,
            @NonNull String fileName,
            @NonNull String contentToUpload,
            @NonNull Callback<ResponseCodes> callback) {

        Call<ResponseCodes> uploadFileCall = getScorocodeApi().upload(new RequestUpload(getAppId(), getClientKey(), getMasterOrFileKey(), getSessionId(), collectionName, documentId, fieldName, fileName, contentToUpload));
        uploadFileCall.enqueue(callback);
    }

    public static String getFileLink(
            @NonNull String collectionName,
            @NonNull String fieldName,
            @NonNull String docId,
            @NonNull String fileName) {

        Call<ResponseCodes> getFileCallback = getScorocodeApi().getFile(getAppId(), collectionName, fieldName, docId, fileName);
        return getFileCallback.request().url().url().toString();
    }

    public static void deleteFile(
            @NonNull String collenctionName,
            @NonNull String docId,
            @NonNull String fieldName,
            @NonNull String fileName,
            @NonNull Callback<ResponseString> callback) {

        Call<ResponseString> deleteFileCall = getScorocodeApi().deleteFile(new RequestFile(getAppId(), getClientKey(), getMasterOrFileKey(), getSessionId(), collenctionName, docId, fieldName, fileName));
        deleteFileCall.enqueue(callback);
    }

    public static void sendEmail(
            @NonNull String collectionName,
            @Nullable Query query,
            @NonNull MessageEmail msg,
            @NonNull Callback<ResponseCodes> callback) {

        Call<ResponseCodes> sendEmailCall = getScorocodeApi().sendEmail(new RequestSendEmail(getAppId(), getClientKey(), getMasterOrFileKey(), getSessionId(), collectionName, query, msg));
        sendEmailCall.enqueue(callback);
    }

    public static void sendPush(
            @NonNull String collectionName,
            @Nullable Query query,
            @NonNull MessagePush msg,
            @NonNull Callback<ResponseCodes> callback) {

        Call<ResponseCodes> sendPushCall = getScorocodeApi().sendPush(new RequestSendPush(getAppId(), getClientKey(), getMasterOrMessageKey(), getSessionId(), collectionName, query, msg));
        sendPushCall.enqueue(callback);
    }

    public static void sendSms(
            @NonNull String collectionName,
            @Nullable Query query,
            @NonNull MessageSms msg,
            @NonNull Callback<ResponseCodes> callback) {

        Call<ResponseCodes> sendSmsCall = getScorocodeApi().sendSms(new RequestSendSms(getAppId(), getClientKey(), getMasterOrMessageKey(), getSessionId(), collectionName, query, msg));
        sendSmsCall.enqueue(callback);
    }

    public static void sendScriptTask(
            @NonNull String scriptId,
            @NonNull HashMap<String, String> dataPoolForScript,
            @NonNull Callback<ResponseCodes> callback) {

        Call<ResponseCodes> sendScriptTask = getScorocodeApi().sendScriptTask(new RequestSendScriptTask(getAppId(), getClientKey(), getMasterOrMessageKey(), getSessionId(), scriptId, dataPoolForScript));
        sendScriptTask.enqueue(callback);
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
        clientBuilder.followRedirects(false);

        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(clientBuilder.build())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static String getAccountKey() {
        return getMasterKey() != null? getMasterKey() : getMessageKey();
    }
}
