package ru.profit_group.scorocode_sdk.Requests.data;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.HashMap;

import ru.profit_group.scorocode_sdk.scorocode_objects.Query;

/**
 * Created by Peter Staranchuk on 5/10/16
 */
public class RequestUpdate {
    private String app;
    private String cli;
    private String acc;
    private String sess;
    private String coll;
    private Query query;
    private HashMap<String, HashMap<String, Object>> doc;
    private Integer limit;

    public RequestUpdate(
            @NonNull String appId,
            @NonNull String clientKey,
            @Nullable String accountKey,
            @NonNull String sessionId,
            @NonNull String collectionName,
            @Nullable Query query,
            @NonNull HashMap<String, HashMap<String, Object>> doc,
            @Nullable Integer limit) {
        this.app = appId;
        this.cli = clientKey;
        this.acc = accountKey;
        this.sess = sessionId;
        this.coll = collectionName;
        this.query = query;
        this.doc = doc;
        this.limit = limit;
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

    public String getSess() {
        return sess;
    }

    public String getColl() {
        return coll;
    }

    public Query getQuery() {
        return query;
    }

    public HashMap<String, HashMap<String, Object>> getDoc() {
        return doc;
    }

    public Integer getLimit() {
        return limit;
    }
}
