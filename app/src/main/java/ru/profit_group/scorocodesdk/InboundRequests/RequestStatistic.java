package ru.profit_group.scorocodesdk.InboundRequests;

import retrofit2.http.Field;

/**
 * Created by peter on 21/09/16
 */
public class RequestStatistic {

    private String app;
    private String cli;
    private String acc;

    public RequestStatistic(String app, String cli, String acc) {
        this.app = app;
        this.cli = cli;
        this.acc = acc;
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
