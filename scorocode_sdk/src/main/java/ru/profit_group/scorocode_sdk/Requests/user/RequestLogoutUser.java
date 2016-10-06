package ru.profit_group.scorocode_sdk.Requests.user;

/**
 * Created by Peter Staranchuk on 5/10/16
 */
public class RequestLogoutUser {
    private String app;
    private String cli;
    private String sess;

    public RequestLogoutUser(String appId, String clientKey, String sessionId) {
        this.app = appId;
        this.cli = clientKey;
        this.sess = sessionId;
    }

    public String getApp() {
        return app;
    }

    public String getCli() {
        return cli;
    }

    public String getSess() {
        return sess;
    }
}
