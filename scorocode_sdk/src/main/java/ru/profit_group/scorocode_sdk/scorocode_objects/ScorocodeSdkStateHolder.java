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

    private String _sessionId;

    public ScorocodeSdkStateHolder(String applicationId, String clientKey, String masterKey, String fileKey, String messageKey, String scriptKey) {
        this.applicationId = applicationId;
        this.clientKey = clientKey;
        this.masterKey = masterKey;
        this.fileKey = fileKey;
        this.messageKey = messageKey;
        this.scriptKey = scriptKey;
    }

    public String getApplicationId() {
        return applicationId; //TODO add exception if null
    }

    public String getClientKey() {
        return clientKey; //TODO add exception if null
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
        return _sessionId;
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

    public void setSessionId(String sessionId) {
        _sessionId = sessionId;
    }
}
