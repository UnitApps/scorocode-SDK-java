package ru.profit_group.scorocode_sdk.Requests.statistic;

/**
 * Created by Peter Staranchuk on 5/10/16
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
