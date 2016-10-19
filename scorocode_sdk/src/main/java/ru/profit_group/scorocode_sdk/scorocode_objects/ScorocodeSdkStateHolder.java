package ru.profit_group.scorocode_sdk.scorocode_objects;

/**
 * Created by Peter Staranchuk on 10/11/16
 */
public class ScorocodeSdkStateHolder {
    private String applicationId;
    private String clientKey;
    private String masterKey;
    private String fileKey;
    private String messageKey;
    private String scriptKey;
    private String webSocket;

    private String sessionId;

    public ScorocodeSdkStateHolder(String applicationId, String clientKey, String masterKey, String fileKey, String messageKey, String scriptKey, String webSocket) {
        this.applicationId = applicationId;
        this.clientKey = clientKey;
        this.masterKey = masterKey;
        this.fileKey = fileKey;
        this.messageKey = messageKey;
        this.scriptKey = scriptKey;
        this.webSocket = webSocket;
    }

    public String getApplicationId() {
        return applicationId;
    }

    public String getClientKey() {
        return clientKey;
    }

    public String getMasterKey() {
        return masterKey;
    }

    public String getFileKey() {
        return fileKey;
    }

    public String getMessageKey() {
        return messageKey;
    }

    public String getSessionId() {
        return sessionId;
    }

    public String getMasterOrFileKey() {
        return masterKey != null? masterKey : fileKey;
    }

    public String getMasterOrMessageKey() {
        return masterKey != null? masterKey : messageKey;
    }

    public String getMasterOrScriptKey() {
        return masterKey != null? masterKey : scriptKey;
    }

    public String getWebSocket() {
        return webSocket;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
}
