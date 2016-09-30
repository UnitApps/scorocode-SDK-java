package ru.profit_group.scorocodesdk.Requests;


import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import ru.profit_group.scorocodesdk.Objects.Query;
import ru.profit_group.scorocodesdk.Objects.Sort;

/**
 * Created by peter on 25/09/16.
 */
public class RequestFind {
    private String app;
    private String cli;
    private String acc;
    private String sess;
    private String coll;
    private Query query;
    private Sort sort;
    private String[] fields;
    private Integer limit;
    private Integer skip;

    public RequestFind(
            @NonNull String app,
            @NonNull String cli,
            @Nullable String acc,
            @NonNull String sess,
            @NonNull String coll,
            @Nullable Query query,
            @Nullable Sort sort,
            @Nullable String[] fields,
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

    public Query getQuery() {
        return query;
    }

    public Sort getSort() {
        return sort;
    }

    public String[] getFields() {
        return fields;
    }

    public Integer getLimit() {
        return limit;
    }

    public Integer getSkip() {
        return skip;
    }
}
