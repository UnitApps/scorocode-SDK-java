package ru.profit_group.scorocodesdk;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
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
import ru.profit_group.scorocodesdk.Responses.ResponseFind;
import ru.profit_group.scorocodesdk.Responses.ResponseInsert;
import ru.profit_group.scorocodesdk.Responses.ResponseLogin;
import ru.profit_group.scorocodesdk.Responses.ResponseAppStatistic;
import ru.profit_group.scorocodesdk.Responses.ResponseRemove;
import ru.profit_group.scorocodesdk.Responses.ResponseUpdate;
import ru.profit_group.scorocodesdk.Responses.ResponseUpdateById;

/**
 * Created by Peter Staranchuk on 20/09/16
 */
public interface ScorocodeApi {

    @Headers({"Content-Type: application/json"})
    @POST("api/v1/stat")
    Call<ResponseAppStatistic> getAppStatistic(@Body RequestStatistic requestStatistic);

    @Headers({"Content-Type: application/json"})
    @POST("api/v1/register")
    Call<ResponseDefault> register(@Body RequestRegisterUser requestRegisterUser);

    @Headers({"Content-Type: application/json"})
    @POST("api/v1/login")
    Call<ResponseLogin> login(@Body RequestLoginUser requestLoginUser);

    @Headers({"Content-Type: application/json"})
    @POST("api/v1/logout")
    Call<ResponseDefault> logout(@Body RequestLogoutUser requestLogoutUser);

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
    @POST("api/v1/data/updateById")
    Call<ResponseUpdateById> updateById(@Body RequestUpdateById requestUpdateById);

    @Headers({"Content-Type: application/json"})
    @POST("api/v1/data/find")
    Call<ResponseFind> find(@Body RequestFind requestFind);

    @Headers({"Content-Type: application/json"})
    @POST("api/v1/data/count")
    Call<ResponseCount> count(@Body RequestCount requestCount);
}
