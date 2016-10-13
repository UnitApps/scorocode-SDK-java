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
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.profit_group.scorocode_sdk.Callbacks.CallbackApplicationStatistic;
import ru.profit_group.scorocode_sdk.Callbacks.CallbackCountDocument;
import ru.profit_group.scorocode_sdk.Callbacks.CallbackDeleteFile;
import ru.profit_group.scorocode_sdk.Callbacks.CallbackFindDocument;
import ru.profit_group.scorocode_sdk.Callbacks.CallbackInsert;
import ru.profit_group.scorocode_sdk.Callbacks.CallbackLoginUser;
import ru.profit_group.scorocode_sdk.Callbacks.CallbackLogoutUser;
import ru.profit_group.scorocode_sdk.Callbacks.CallbackRegisterUser;
import ru.profit_group.scorocode_sdk.Callbacks.CallbackRemoveDocument;
import ru.profit_group.scorocode_sdk.Callbacks.CallbackSendEmail;
import ru.profit_group.scorocode_sdk.Callbacks.CallbackSendPush;
import ru.profit_group.scorocode_sdk.Callbacks.CallbackSendScript;
import ru.profit_group.scorocode_sdk.Callbacks.CallbackSendSms;
import ru.profit_group.scorocode_sdk.Callbacks.CallbackUpdateDocument;
import ru.profit_group.scorocode_sdk.Callbacks.CallbackUpdateDocumentById;
import ru.profit_group.scorocode_sdk.Callbacks.CallbackUploadFile;
import ru.profit_group.scorocode_sdk.Requests.messages.MessageEmail;
import ru.profit_group.scorocode_sdk.Requests.messages.MessagePush;
import ru.profit_group.scorocode_sdk.Requests.messages.MessageSms;
import ru.profit_group.scorocode_sdk.scorocode_objects.Document;
import ru.profit_group.scorocode_sdk.scorocode_objects.Query;
import ru.profit_group.scorocode_sdk.scorocode_objects.ScorocodeSdkStateHolder;
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
    public static final String ERROR_MESSAGE_GENERAL = "server not answered on request";
    public static final String ERROR_CODE_GENERAL = "-1";

    private static ScorocodeSdkStateHolder stateHolder;
    private static Retrofit retrofitInstance; //Singleton

    public static void initWith(
            @NonNull String applicationId,
            @NonNull String clientKey,
            @Nullable String masterKey,
            @Nullable String fileKey,
            @Nullable String messageKey,
            @Nullable String scriptKey) {

        stateHolder = new ScorocodeSdkStateHolder(applicationId, clientKey, masterKey, fileKey, messageKey, scriptKey);
    }

    public static void initWith(@NonNull String applicationId, @NonNull String clientKey) {
        initWith(applicationId, clientKey, null, null, null, null);
    }

    public static void getApplicationStatistic(
            @NonNull final CallbackApplicationStatistic callbackApplicationStatistic) throws IOException {

        Call<ResponseAppStatistic> appStatisticCall = getScorocodeApi().getAppStatistic(new RequestStatistic(stateHolder));
        appStatisticCall.enqueue(new Callback<ResponseAppStatistic>() {
            @Override
            public void onResponse(Call<ResponseAppStatistic> call, Response<ResponseAppStatistic> response) {
                if(response != null && response.body() != null) {
                    ResponseAppStatistic responseAppStatistic = response.body();
                    if(responseSuccseed(responseAppStatistic)) {
                        callbackApplicationStatistic.onRequestSucceed(responseAppStatistic);
                    } else {
                        callbackApplicationStatistic.onRequestFailed(responseAppStatistic.getErrCode(), responseAppStatistic.getErrMsg());
                    }
                } else {
                    callbackApplicationStatistic.onRequestFailed(ERROR_CODE_GENERAL, ERROR_MESSAGE_GENERAL);
                }
            }

            @Override
            public void onFailure(Call<ResponseAppStatistic> call, Throwable t) {
                callbackApplicationStatistic.onRequestFailed(ERROR_CODE_GENERAL, t.getMessage());
            }
        });
    }

    public static void registerUser(
            @NonNull String userName,
            @NonNull String userEmail,
            @NonNull String userPassword,
            @Nullable HashMap<String, String>  doc,
            @NonNull final CallbackRegisterUser callbackRegisterUser) {

        Call<ResponseCodes> registerUserCall = getScorocodeApi().register(new RequestRegisterUser(stateHolder, userName, userEmail, userPassword, doc));
        registerUserCall.enqueue(new Callback<ResponseCodes>() {
            @Override
            public void onResponse(Call<ResponseCodes> call, Response<ResponseCodes> response) {
                if(response != null && response.body() != null) {
                    ResponseCodes responseCodes = response.body();
                    if(responseSuccseed(responseCodes)) {
                        callbackRegisterUser.onRegisterSucceed();
                    } else {
                        callbackRegisterUser.onRegisterFailed(responseCodes.getErrCode(), responseCodes.getErrMsg());
                    }
                } else {
                    callbackRegisterUser.onRegisterFailed(ERROR_CODE_GENERAL, ERROR_MESSAGE_GENERAL);
                }
            }

            @Override
            public void onFailure(Call<ResponseCodes> call, Throwable t) {
                callbackRegisterUser.onRegisterFailed(ERROR_CODE_GENERAL, t.getMessage());
            }
        });
    }

    public static void loginUser(
            @NonNull String email,
            @NonNull String password,
            @NonNull final CallbackLoginUser callbackLogin) {

        Call<ResponseLogin> loginUserCall = getScorocodeApi().login(new RequestLoginUser(stateHolder, email, password));
        loginUserCall.enqueue(new Callback<ResponseLogin>() {
            @Override
            public void onResponse(Call<ResponseLogin> call, Response<ResponseLogin> response) {
                if(response != null && response.body() != null) {
                    ResponseLogin responseLogin = response.body();
                    if(responseSuccseed(responseLogin)) {
                        callbackLogin.onLoginSucceed(responseLogin);
                    } else {
                        callbackLogin.onLoginFailed(responseLogin.getErrCode(), responseLogin.getErrMsg());
                    }
                } else {
                    callbackLogin.onLoginFailed(ERROR_CODE_GENERAL, ERROR_MESSAGE_GENERAL);
                }
            }

            @Override
            public void onFailure(Call<ResponseLogin> call, Throwable t) {
                callbackLogin.onLoginFailed(ERROR_CODE_GENERAL, t.getMessage());
            }
        });
    }

    public static void logoutUser(
            @NonNull final CallbackLogoutUser callbackLogout) {

        Call<ResponseCodes> logoutUserCall = getScorocodeApi().logout(new RequestLogoutUser(stateHolder));
        logoutUserCall.enqueue(new Callback<ResponseCodes>() {
            @Override
            public void onResponse(Call<ResponseCodes> call, Response<ResponseCodes> response) {
                if(response != null && response.body() != null) {
                    ResponseCodes responseCodes = response.body();
                    if(responseSuccseed(responseCodes)) {
                        callbackLogout.onLogoutSucceed();
                        ScorocodeSdk.setSessionId(null);
                    } else {
                        callbackLogout.onLogoutFailed(responseCodes.getErrCode(), responseCodes.getErrMsg());
                    }
                } else {
                    callbackLogout.onLogoutFailed(ERROR_CODE_GENERAL, ERROR_MESSAGE_GENERAL);
                }
            }

            @Override
            public void onFailure(Call<ResponseCodes> call, Throwable t) {
                callbackLogout.onLogoutFailed(ERROR_CODE_GENERAL, t.getMessage());
            }
        });
    }

    public static void insertDocument(
            @NonNull String collectionName,
            @Nullable HashMap<String, String> doc,
            @NonNull final CallbackInsert callbackInsert) {

        Call<ResponseInsert> insertCall = getScorocodeApi().insert(new RequestInsert(stateHolder, collectionName, doc));
        insertCall.enqueue(new Callback<ResponseInsert>() {
            @Override
            public void onResponse(Call<ResponseInsert> call, Response<ResponseInsert> response) {
                if(response != null && response.body() != null) {
                    ResponseInsert responseInsert = response.body();
                    if(responseSuccseed(responseInsert)) {
                        callbackInsert.onInsertSucceed(responseInsert);
                    } else {
                        callbackInsert.onInsertFailed(responseInsert.getErrCode(), responseInsert.getErrMsg());
                    }
                } else {
                    callbackInsert.onInsertFailed(ERROR_CODE_GENERAL, ERROR_MESSAGE_GENERAL);
                }
            }

            @Override
            public void onFailure(Call<ResponseInsert> call, Throwable t) {
                callbackInsert.onInsertFailed(ERROR_CODE_GENERAL, t.getMessage());
            }
        });
    }

    public static void removeDocument(
            @NonNull String collectionName,
            @Nullable Query query,
            @Nullable Integer limit,
            final CallbackRemoveDocument callbackRemoveDocument) {

        Call<ResponseRemove> removeCall = getScorocodeApi().remove(new RequestRemove(stateHolder, collectionName, query, limit));
        removeCall.enqueue(new Callback<ResponseRemove>() {
            @Override
            public void onResponse(Call<ResponseRemove> call, Response<ResponseRemove> response) {
                if(response != null && response.body() != null) {
                    ResponseRemove responseRemove = response.body();
                    if(responseSuccseed(responseRemove)) {
                        callbackRemoveDocument.onRemoveSucceed(responseRemove);
                    } else {
                        callbackRemoveDocument.onRemoveFailed(responseRemove.getErrCode(), responseRemove.getErrMsg());
                    }
                } else {
                    callbackRemoveDocument.onRemoveFailed(ERROR_CODE_GENERAL, ERROR_MESSAGE_GENERAL);
                }
            }

            @Override
            public void onFailure(Call<ResponseRemove> call, Throwable t) {
                callbackRemoveDocument.onRemoveFailed(ERROR_CODE_GENERAL, t.getMessage());
            }
        });
    }

    public static void updateDocument(
            @NonNull String collectionName,
            @Nullable Query query,
            @NonNull HashMap<String, HashMap<String, Object>> doc,
            @Nullable Integer limit,
            final CallbackUpdateDocument callbackUpdateDocument) {

        Call<ResponseUpdate> updateCall = getScorocodeApi().update(new RequestUpdate(stateHolder, collectionName, query, doc, limit));
        updateCall.enqueue(new Callback<ResponseUpdate>() {
            @Override
            public void onResponse(Call<ResponseUpdate> call, Response<ResponseUpdate> response) {
                if(response != null && response.body() != null) {
                    ResponseUpdate responseUpdate = response.body();
                    if(responseSuccseed(responseUpdate)) {
                        callbackUpdateDocument.onUpdateSucceed(responseUpdate);
                    } else {
                        callbackUpdateDocument.onUpdateFailed(responseUpdate.getErrCode(), responseUpdate.getErrMsg());
                    }
                } else {
                    callbackUpdateDocument.onUpdateFailed(ERROR_CODE_GENERAL, ERROR_MESSAGE_GENERAL);
                }
            }

            @Override
            public void onFailure(Call<ResponseUpdate> call, Throwable t) {
                callbackUpdateDocument.onUpdateFailed(ERROR_CODE_GENERAL, t.getMessage());
            }
        });
    }

    public static void updateDocumentById(
            @NonNull String collectionName,
            @NonNull HashMap<String, String> query,
            @NonNull HashMap<String, HashMap<String,Object>> doc,
            final CallbackUpdateDocumentById callbackUpdateDocumentById) {

        Call<ResponseUpdateById> updateByIdCall = getScorocodeApi().updateById(new RequestUpdateById(stateHolder, collectionName, query, doc));
        updateByIdCall.enqueue(new Callback<ResponseUpdateById>() {
            @Override
            public void onResponse(Call<ResponseUpdateById> call, Response<ResponseUpdateById> response) {
                if(response != null && response.body() != null) {
                    ResponseUpdateById responseUpdateById = response.body();
                    if(responseSuccseed(responseUpdateById)) {
                        callbackUpdateDocumentById.onUpdateByIdSucceed(responseUpdateById);
                    } else {
                        callbackUpdateDocumentById.onUpdateByIdFailed(responseUpdateById.getErrCode(), responseUpdateById.getErrMsg());
                    }
                } else {
                    callbackUpdateDocumentById.onUpdateByIdFailed(ERROR_CODE_GENERAL, ERROR_MESSAGE_GENERAL);
                }
            }

            @Override
            public void onFailure(Call<ResponseUpdateById> call, Throwable t) {
                callbackUpdateDocumentById.onUpdateByIdFailed(ERROR_CODE_GENERAL, t.getMessage());
            }
        });
    }

    public static void findDocument(
            @NonNull String collectionName,
            @Nullable Query query,
            @Nullable Sort sort,
            @Nullable List<String> fieldsNamesToFind,
            @Nullable Integer limit,
            @Nullable Integer skip,
            final CallbackFindDocument callbackFindDocument) {

        Call<ResponseString> findCall = getScorocodeApi().find(new RequestFind(stateHolder, collectionName, query, sort, fieldsNamesToFind, limit, skip));
        findCall.enqueue(new Callback<ResponseString>() {
            @Override
            public void onResponse(Call<ResponseString> call, Response<ResponseString> response) {
                if(response != null && response.body() != null) {
                    ResponseString responseFindDocument = response.body();
                    if(responseSuccseed(responseFindDocument)) {
                        String base64data = response.body().getResult();
                        callbackFindDocument.onDocumentFound(Document.decodeDocumentsList(base64data));
                    } else {
                        callbackFindDocument.onDocumentNotFound(responseFindDocument.getErrCode(), responseFindDocument.getErrMsg());
                    }
                } else {
                    callbackFindDocument.onDocumentNotFound(ERROR_CODE_GENERAL, ERROR_MESSAGE_GENERAL);
                }
            }

            @Override
            public void onFailure(Call<ResponseString> call, Throwable t) {
                callbackFindDocument.onDocumentNotFound(ERROR_CODE_GENERAL, t.getMessage());
            }
        });
    }

    public static void getDocumentsCount(
            @NonNull String collectionName,
            @Nullable Query query,
            final CallbackCountDocument callbackCountDocument) {

        Call<ResponseCount> callCount = getScorocodeApi().count(new RequestCount(stateHolder, collectionName, query));
        callCount.enqueue(new Callback<ResponseCount>() {
            @Override
            public void onResponse(Call<ResponseCount> call, Response<ResponseCount> response) {
                if(response != null && response.body() != null) {
                    ResponseCount responseCount = response.body();
                    if(responseSuccseed(responseCount)) {
                        callbackCountDocument.onDocumentsCounted(responseCount);
                    } else {
                        callbackCountDocument.onCountFailed(responseCount.getErrCode(), responseCount.getErrMsg());
                    }
                } else {
                    callbackCountDocument.onCountFailed(ERROR_CODE_GENERAL, ERROR_MESSAGE_GENERAL);
                }
            }

            @Override
            public void onFailure(Call<ResponseCount> call, Throwable t) {
                callbackCountDocument.onCountFailed(ERROR_CODE_GENERAL, t.getMessage());
            }
        });
    }

    public static void uploadFile(
            @NonNull String collectionName,
            @NonNull String documentId,
            @NonNull String fieldName,
            @NonNull String fileName,
            @NonNull String contentToUpload,
            @NonNull final CallbackUploadFile callbackUploadFile) {

        Call<ResponseCodes> uploadFileCall = getScorocodeApi().upload(new RequestUpload(stateHolder, collectionName, documentId, fieldName, fileName, contentToUpload));
        uploadFileCall.enqueue(new Callback<ResponseCodes>() {
            @Override
            public void onResponse(Call<ResponseCodes> call, Response<ResponseCodes> response) {
                if(response != null && response.body() != null) {
                    ResponseCodes responseCodes = response.body();
                    if(responseSuccseed(responseCodes)) {
                        callbackUploadFile.onDocumentUploaded();
                    } else {
                        callbackUploadFile.onDocumentUploadFailed(responseCodes.getErrCode(), responseCodes.getErrMsg());
                    }
                } else {
                    callbackUploadFile.onDocumentUploadFailed(ERROR_CODE_GENERAL, ERROR_MESSAGE_GENERAL);
                }
            }

            @Override
            public void onFailure(Call<ResponseCodes> call, Throwable t) {
                callbackUploadFile.onDocumentUploadFailed(ERROR_CODE_GENERAL, t.getMessage());
            }
        });
    }

    public static String getFileLink(
            @NonNull String collectionName,
            @NonNull String fieldName,
            @NonNull String docId,
            @NonNull String fileName) {

        Call<ResponseCodes> getFileCallback = getScorocodeApi().getFile(stateHolder.getApplicationId(), collectionName, fieldName, docId, fileName);
        return getFileCallback.request().url().url().toString();
    }

    public static void deleteFile(
            @NonNull String collectionName,
            @NonNull String docId,
            @NonNull String fieldName,
            @NonNull String fileName,
            @NonNull final CallbackDeleteFile callbackDeleteFile) {

        Call<ResponseCodes> deleteFileCall = getScorocodeApi().deleteFile(new RequestFile(stateHolder, collectionName, docId, fieldName, fileName));
        deleteFileCall.enqueue(new Callback<ResponseCodes>() {
            @Override
            public void onResponse(Call<ResponseCodes> call, Response<ResponseCodes> response) {
                if(response != null && response.body() != null) {
                    ResponseCodes responseCodes = response.body();
                    if(responseSuccseed(responseCodes)) {
                        callbackDeleteFile.onDocumentDeleted();
                    } else {
                        callbackDeleteFile.onDetelionFailed(responseCodes.getErrCode(), responseCodes.getErrMsg());
                    }
                } else {
                    callbackDeleteFile.onDetelionFailed(ERROR_CODE_GENERAL, ERROR_MESSAGE_GENERAL);
                }
            }

            @Override
            public void onFailure(Call<ResponseCodes> call, Throwable t) {
                callbackDeleteFile.onDetelionFailed(ERROR_CODE_GENERAL, t.getMessage());
            }
        });
    }

    public static void sendEmail(
            @NonNull String collectionName,
            @Nullable Query query,
            @NonNull MessageEmail msg,
            @NonNull final CallbackSendEmail callbackSendEmail) {

        Call<ResponseCodes> sendEmailCall = getScorocodeApi().sendEmail(new RequestSendEmail(stateHolder, collectionName, query, msg));
        sendEmailCall.enqueue(new Callback<ResponseCodes>() {
            @Override
            public void onResponse(Call<ResponseCodes> call, Response<ResponseCodes> response) {
                if(response != null && response.body() != null) {
                    ResponseCodes responseCodes = response.body();
                    if(responseSuccseed(responseCodes)) {
                        callbackSendEmail.onEmailSend();
                    } else {
                        callbackSendEmail.onEmailSendFailed(responseCodes.getErrCode(), responseCodes.getErrMsg());
                    }
                } else {
                    callbackSendEmail.onEmailSendFailed(ERROR_CODE_GENERAL, ERROR_MESSAGE_GENERAL);
                }
            }

            @Override
            public void onFailure(Call<ResponseCodes> call, Throwable t) {
                callbackSendEmail.onEmailSendFailed(ERROR_CODE_GENERAL, t.getMessage());
            }
        });
    }

    public static void sendPush(
            @NonNull String collectionName,
            @Nullable Query query,
            @NonNull MessagePush msg,
            @NonNull final CallbackSendPush callbackSendPush) {

        Call<ResponseCodes> sendPushCall = getScorocodeApi().sendPush(new RequestSendPush(stateHolder, collectionName, query, msg));
        sendPushCall.enqueue(new Callback<ResponseCodes>() {
            @Override
            public void onResponse(Call<ResponseCodes> call, Response<ResponseCodes> response) {
                if(response != null && response.body() != null) {
                    ResponseCodes responseCodes = response.body();
                    if(responseSuccseed(responseCodes)) {
                        callbackSendPush.onPushSended();
                    } else {

                        callbackSendPush.onPushSendFailed(responseCodes.getErrCode(), responseCodes.getErrMsg());
                    }
                } else {
                    callbackSendPush.onPushSendFailed(ERROR_CODE_GENERAL, ERROR_MESSAGE_GENERAL);
                }
            }

            @Override
            public void onFailure(Call<ResponseCodes> call, Throwable t) {
                callbackSendPush.onPushSendFailed(ERROR_CODE_GENERAL, t.getMessage());
            }
        });
    }

    public static void sendSms(
            @NonNull String collectionName,
            @Nullable Query query,
            @NonNull MessageSms msg,
            @NonNull final CallbackSendSms callbackSendSms) {

        Call<ResponseCodes> sendSmsCall = getScorocodeApi().sendSms(new RequestSendSms(stateHolder, collectionName, query, msg));
        sendSmsCall.enqueue(new Callback<ResponseCodes>() {
            @Override
            public void onResponse(Call<ResponseCodes> call, Response<ResponseCodes> response) {
                if(response != null && response.body() != null) {
                    ResponseCodes responseCodes = response.body();
                    if(responseSuccseed(responseCodes)) {
                        callbackSendSms.onSmsSended();
                    } else {
                        callbackSendSms.onSmsSendFailed(responseCodes.getErrCode(), responseCodes.getErrMsg());
                    }
                } else {
                    callbackSendSms.onSmsSendFailed(ERROR_CODE_GENERAL, ERROR_MESSAGE_GENERAL);
                }
            }

            @Override
            public void onFailure(Call<ResponseCodes> call, Throwable t) {
                callbackSendSms.onSmsSendFailed(ERROR_CODE_GENERAL, t.getMessage());
            }
        });
    }

    public static void sendScriptTask(
            @NonNull String scriptId,
            @NonNull HashMap<String, String> dataPoolForScript,
            @NonNull final CallbackSendScript callbackSendScript) {

        Call<ResponseCodes> sendScriptTask = getScorocodeApi().sendScriptTask(new RequestSendScriptTask(stateHolder, scriptId, dataPoolForScript));
        sendScriptTask.enqueue(new Callback<ResponseCodes>() {
            @Override
            public void onResponse(Call<ResponseCodes> call, Response<ResponseCodes> response) {
                if(response != null && response.body() != null) {
                    ResponseCodes responseCodes = response.body();
                    if(responseSuccseed(responseCodes)) {
                        callbackSendScript.onScriptSended();
                    } else {
                        callbackSendScript.onScriptSendFailed(responseCodes.getErrCode(), responseCodes.getErrMsg());
                    }
                } else {
                    callbackSendScript.onScriptSendFailed(ERROR_CODE_GENERAL, ERROR_MESSAGE_GENERAL);
                }
            }

            @Override
            public void onFailure(Call<ResponseCodes> call, Throwable t) {
                callbackSendScript.onScriptSendFailed(ERROR_CODE_GENERAL, t.getMessage());
            }
        });
    }

    private static ScorocodeApi getScorocodeApi() {
        return getRetrofit().create(ScorocodeApi.class);
    }

    @NonNull
    private static Retrofit getRetrofit() {
        if(retrofitInstance == null) {

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
        } else {
            return retrofitInstance;
        }
    }

    public static void setSessionId(String sessionId) {
        if(stateHolder != null) {
            stateHolder.setSessionId(sessionId);
        }
    }

    private static boolean responseSuccseed(ResponseCodes responseCodes) {
        return !responseCodes.isError();
    }

}
