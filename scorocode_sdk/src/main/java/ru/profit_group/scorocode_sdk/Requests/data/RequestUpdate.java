package ru.profit_group.scorocode_sdk.Requests.data;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.HashMap;

/**
 * Created by Peter Staranchuk on 5/10/16
 */
public class RequestUpdate {
    private String app;
    private String cli;
    private String acc;
    private String sess;
    private String coll;
    private HashMap<String, HashMap<String,String>> query;
    private HashMap<String, HashMap<String, String>> doc;
    private Long limit;

    public RequestUpdate(
            @NonNull String appId,
            @NonNull String clientKey,
            @Nullable String accountKey,
            @NonNull String sessionId,
            @NonNull String collectionName,
            @Nullable HashMap<String, HashMap<String,String>> query,
            @NonNull HashMap<String, HashMap<String, String>> doc,
            @Nullable Long limit) {
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

    public HashMap<String, HashMap<String,String>> getQuery() {
        return query;
    }

    public HashMap<String, HashMap<String, String>> getDoc() {
        return doc;
    }

    public Long getLimit() {
        return limit;
    }
}
