package ru.profit_group.scorocode_sdk.Requests.statistic;

import ru.profit_group.scorocode_sdk.scorocode_objects.ScorocodeSdkStateHolder;

/**
 * Created by Peter Staranchuk on 5/10/16
 */
public class RequestStatistic {

    private String app;
    private String cli;
    private String acc;

    public RequestStatistic(ScorocodeSdkStateHolder stateHolder) {
        this.app = stateHolder.getApplicationId();
        this.cli = stateHolder.getClientKey();
        this.acc = stateHolder.getMasterKey();
    }

    public String getApp() {
        return app;
    }

    public String getCli() {
        return cli;
    }

    public String getAcc() {
        return acc;
    }
}
