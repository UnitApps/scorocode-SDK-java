package ru.profit_group.scorocode_sdk.Requests;

/**
 * Created by peter staranchuk on 21/09/16.
 */
public class RequestLogoutUser {
    private String appId;
    private String clientKey;
    private String sessionId;

    public RequestLogoutUser(String appId, String clientKey, String sessionId) {
        this.appId = appId;
        this.clientKey = clientKey;
        this.sessionId = sessionId;
    }

    public String getAppId() {
        return appId;
    }

    public String getClientKey() {
        return clientKey;
    }

    public String getSessionId() {
        return sessionId;
    }
}
