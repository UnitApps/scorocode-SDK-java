package ru.profit_group.scorocodesdk.Requests;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.HashMap;

import ru.profit_group.scorocodesdk.Objects.Query;

/**
 * Created by peter staranchuk on 21/09/16.
 */
public class RequestRemove {
    private String appId;
    private String clientKey;
    private String accessKey;
    private String sessionId;
    private String collectionName;
    private Query query;
    private Integer limit;

    public RequestRemove(
            @NonNull String appId,
            @NonNull String clientKey,
            @Nullable String accessKey,
            @NonNull String sessionId,
            @NonNull String collectionName,
            @Nullable Query query,
            @Nullable Integer limit) {
        this.appId = appId;
        this.clientKey = clientKey;
        this.accessKey = accessKey;
        this.sessionId = sessionId;
        this.collectionName = collectionName;
        this.query = query;
        this.limit = limit;
    }

    public String getAppId() {
        return appId;
    }

    public String getClientKey() {
        return clientKey;
    }

    public String getAccessKey() {
        return accessKey;
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

    public Integer getLimit() {
        return limit;
    }
}
