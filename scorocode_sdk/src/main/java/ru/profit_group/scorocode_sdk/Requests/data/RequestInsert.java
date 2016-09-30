package ru.profit_group.scorocode_sdk.Requests.data;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.HashMap;

/**
 * Created by Peter Staranchuk on 5/10/16
 */
public class RequestInsert {
    private String app;
    private String cli;
    private String acc;
    private String sess;
    private String coll;
    private HashMap<String, String> doc;

    public RequestInsert(
            @NonNull String appId,
            @NonNull String clientId,
            @Nullable String accsessKey,
            @NonNull String sessionId,
            @NonNull String collectionName,
            @Nullable HashMap<String, String> doc) {
        this.app = appId;
        this.cli = clientId;
        this.acc = accsessKey;
        this.sess = sessionId;
        this.coll = collectionName;
        this.doc = doc;
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

    public HashMap<String, String> getDoc() {
        return doc;
    }
}
