package ru.profit_group.scorocodesdk;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import ru.profit_group.scorocodesdk.InboundRequests.RequestStatistic;
import ru.profit_group.scorocodesdk.pojo_statistic.AppStatistic;

/**
 * Created by Peter Staranchuk on 20/09/16
 */
public interface ScorocodeApi {

    @Headers({"Content-Type: application/json"})
    @POST("api/v1/stat")
    Call<AppStatistic> getAppStatistic(@Body RequestStatistic requestStatistic);


}
