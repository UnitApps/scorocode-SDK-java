package ru.profit_group.scorocodesdk.Requests;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import ru.profit_group.scorocodesdk.Objects.Query;

/**
 * Created by peter on 25/09/16.
 */
public class RequestCount {
    private String app;
    private String cli;
    private String acc;
    private String sess;
    private String coll;
    private Query query;

    public RequestCount(
            @NonNull String app,
            @NonNull String cli,
            @Nullable String acc,
            @NonNull String sess,
            @NonNull String coll,
            @Nullable Query query) {
        this.app = app;
        this.cli = cli;
        this.acc = acc;
        this.sess = sess;
        this.coll = coll;
        this.query = query;
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
}