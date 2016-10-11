package ru.profit_group.scorocode_sdk.Requests.data;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.HashMap;

/**
 * Created by Peter Staranchuk on 5/10/16
 */
public class RequestRemove {
    private String app;
    private String cli;
    private String acc;
    private String sess;
    private String coll;
    private HashMap<String, HashMap<String, String>> query;
    private Integer limit;

    public RequestRemove(
            @NonNull String appId,
            @NonNull String clientKey,
            @Nullable String accessKey,
            @NonNull String sessionId,
            @NonNull String collectionName,
            @Nullable HashMap<String, HashMap<String, String>> query,
            @Nullable Integer limit) {
        this.app = appId;
        this.cli = clientKey;
        this.acc = accessKey;
        this.sess = sessionId;
        this.coll = collectionName;
        this.query = query;
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

    public HashMap<String, HashMap<String, String>> getQuery() {
        return query;
    }

    public Integer getLimit() {
        return limit;
    }
}
