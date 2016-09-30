package ru.profit_group.scorocodesdk.Requests;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.HashMap;

import ru.profit_group.scorocodesdk.Objects.Query;

/**
 * Created by peter on 25/09/16.
 */
public class RequestUpdateById {
    private String appId;
    private String clientKey;
    private String accountKey;
    private String sessionId;
    private String collectionName;
    private Query query;
    private HashMap<String, String> doc;

    public RequestUpdateById(
            @NonNull String appId,
            @NonNull String clientKey,
            @Nullable String accountKey,
            @NonNull String sessionId,
            @NonNull String collectionName,
            @Nullable Query query,
            @NonNull HashMap<String, String> doc) {
        this.appId = appId;
        this.clientKey = clientKey;
        this.accountKey = accountKey;
        this.sessionId = sessionId;
        this.collectionName = collectionName;
        this.query = query;
        this.doc = doc;
    }

    public String getAppId() {
        return appId;
    }

    public String getClientKey() {
        return clientKey;
    }

    public String getAccountKey() {
        return accountKey;
    }

    public String getSessionId() {
        return sessionId;
    }

    public String getCollectionName() {
        return collectionName;
    }

    public Query getQuery() {
        return query;
    }

    public HashMap<String, String> getDoc() {
        return doc;
    }
}
