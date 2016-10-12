package ru.profit_group.scorocode_sdk;

import android.support.annotation.NonNull;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
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
import ru.profit_group.scorocode_sdk.Responses.data.ResponseCount;
import ru.profit_group.scorocode_sdk.Responses.ResponseCodes;
import ru.profit_group.scorocode_sdk.Responses.data.ResponseInsert;
import ru.profit_group.scorocode_sdk.Responses.ResponseString;
import ru.profit_group.scorocode_sdk.Responses.user.ResponseLogin;
import ru.profit_group.scorocode_sdk.Responses.statistic.ResponseAppStatistic;
import ru.profit_group.scorocode_sdk.Responses.data.ResponseRemove;
import ru.profit_group.scorocode_sdk.Responses.data.ResponseUpdate;
import ru.profit_group.scorocode_sdk.Responses.data.ResponseUpdateById;

/**
 * Created by Peter Staranchuk on 5/10/16
 */
public interface ScorocodeApi {

    @Headers({"Content-Type: application/json"})
    @POST("api/v1/stat")
    Call<ResponseAppStatistic> getAppStatistic(@Body RequestStatistic requestStatistic);

    @Headers({"Content-Type: application/json"})
    @POST("api/v1/register")
    Call<ResponseCodes> register(@Body RequestRegisterUser requestRegisterUser);

    @Headers({"Content-Type: application/json"})
    @POST("api/v1/login")
    Call<ResponseLogin> login(@Body RequestLoginUser requestLoginUser);

    @Headers({"Content-Type: application/json"})
    @POST("api/v1/logout")
    Call<ResponseCodes> logout(@Body RequestLogoutUser requestLogoutUser);

    @Headers({"Content-Type: application/json"})
    @POST("api/v1/data/insert")
    Call<ResponseInsert> insert(@Body RequestInsert requestInsert);

    @Headers({"Content-Type: application/json"})
    @POST("api/v1/data/remove")
    Call<ResponseRemove> remove(@Body RequestRemove requestRemove);

    @Headers({"Content-Type: application/json"})
    @POST("api/v1/data/update")
    Call<ResponseUpdate> update(@Body RequestUpdate requestUpdate);

    @Headers({"Content-Type: application/json"})
    @POST("api/v1/data/updatebyid")
    Call<ResponseUpdateById> updateById(@Body RequestUpdateById requestUpdateById);

    @Headers({"Content-Type: application/json"})
    @POST("api/v1/data/find")
    Call<ResponseString> find(@Body RequestFind requestFind);

    @Headers({"Content-Type: application/json"})
    @POST("api/v1/data/count")
    Call<ResponseCount> count(@Body RequestCount requestCount);

    @Headers({"Content-Type: application/json"})
    @POST("api/v1/upload")
    Call<ResponseCodes> upload(@Body RequestUpload requestUpload);

    @GET("api/v1/getfile/{app}/{coll}/{field}/{docId}/{file}")
    Call<ResponseCodes> getFile(
            @NonNull @Path("app") String app,
            @NonNull @Path("coll") String coll,
            @NonNull @Path("field") String field,
            @NonNull @Path("docId") String docId,
            @NonNull @Path("file") String file);

    @Headers({"Content-Type: application/json"})
    @POST("api/v1/deletefile")
    Call<ResponseCodes> deleteFile(@Body RequestFile requestDeleteFile);

    @Headers({"Content-Type: application/json"})
    @POST("api/v1/sendemail")
    Call<ResponseCodes> sendEmail(@Body RequestSendEmail requestSendEmail);

    @Headers({"Content-Type: application/json"})
    @POST("api/v1/sendpush")
    Call<ResponseCodes> sendPush(@Body RequestSendPush requestSendPush);

    @Headers({"Content-Type: application/json"})
    @POST("api/v1/sendsms")
    Call<ResponseCodes> sendSms(@Body RequestSendSms requestSendSms);

    @Headers({"Content-Type: application/json"})
    @POST("api/v1/scripts")
    Call<ResponseCodes> sendScriptTask(@Body RequestSendScriptTask requestSendScriptTask);
}

