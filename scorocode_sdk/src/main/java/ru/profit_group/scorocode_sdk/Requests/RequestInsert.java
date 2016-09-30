package ru.profit_group.scorocode_sdk.Requests;

import java.util.HashMap;

/**
 * Created by peter staranchuk on 21/09/16.
 */
public class RequestInsert {
    private String app;
    private String cli;
    private String acc;
    private String sess;
    private String coll;
    private HashMap<String, String> doc;

    public RequestInsert(String appId, String clientId, String accsessKey, String sessionId, String collectionName, HashMap<String, String> doc) {
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
