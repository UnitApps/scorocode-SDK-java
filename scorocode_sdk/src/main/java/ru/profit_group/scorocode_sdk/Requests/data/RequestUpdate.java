package ru.profit_group.scorocode_sdk.Requests.data;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.HashMap;

import ru.profit_group.scorocode_sdk.scorocode_objects.Query;
import ru.profit_group.scorocode_sdk.scorocode_objects.QueryInfo;
import ru.profit_group.scorocode_sdk.scorocode_objects.ScorocodeSdkStateHolder;

/**
 * Created by Peter Staranchuk on 5/10/16
 */
public class RequestUpdate {
    private String app;
    private String cli;
    private String acc;
    private String sess;
    private String coll;
    private QueryInfo query;
    private HashMap<String, HashMap<String, Object>> doc;
    private Integer limit;

    public RequestUpdate(
            @NonNull ScorocodeSdkStateHolder stateHolder,
            @NonNull String collectionName,
            @Nullable Query query,
            @NonNull HashMap<String, HashMap<String, Object>> doc,
            @Nullable Integer limit) {

        this.app = stateHolder.getApplicationId();
        this.cli = stateHolder.getClientKey();
        this.acc = stateHolder.getMasterKey();
        this.sess = stateHolder.getSessionId();
        this.coll = collectionName;
        this.query = query.getQueryInfo();
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

    public QueryInfo getQuery() {
        return query;
    }

    public HashMap<String, HashMap<String, Object>> getDoc() {
        return doc;
    }

    public Integer getLimit() {
        return limit;
    }
}
