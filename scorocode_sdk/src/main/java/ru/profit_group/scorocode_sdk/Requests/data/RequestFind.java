package ru.profit_group.scorocode_sdk.Requests.data;


import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Peter Staranchuk on 5/10/16
 */
public class RequestFind {
    private String app;
    private String cli;
    private String acc;
    private String sess;
    private String coll;
    private HashMap<String, HashMap<String,String>> query;
    private HashMap<String, Integer> sort;
    private List<String> fields;
    private Integer limit;
    private Integer skip;

    public RequestFind(
            @NonNull String app,
            @NonNull String cli,
            @Nullable String acc,
            @NonNull String sess,
            @NonNull String coll,
            @Nullable HashMap<String, HashMap<String,String>> query,
            @Nullable HashMap<String, Integer> sort,
            @Nullable List<String> fields,
            @Nullable Integer limit,
            @Nullable Integer skip) {
        this.app = app;
        this.cli = cli;
        this.acc = acc;
        this.sess = sess;
        this.coll = coll;
        this.query = query;
        this.sort = sort;
        this.fields = fields;
        this.limit = limit;
        this.skip = skip;
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

    public HashMap<String, Integer> getSort() {
        return sort;
    }

    public List<String> getFields() {
        return fields;
    }

    public Integer getLimit() {
        return limit;
    }

    public Integer getSkip() {
        return skip;
    }
}
