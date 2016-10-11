package ru.profit_group.scorocode_sdk.Requests.messages;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import ru.profit_group.scorocode_sdk.scorocode_objects.Query;

/**
 * Created by Peter Staranchuk on 5/10/2016
 */

public class RequestSendPush {
    private String app;
    private String cli;
    private String acc;
    private String sess;
    private String coll;
    private Query query;
    private MessagePush msg;

    public RequestSendPush(
            @NonNull String app,
            @NonNull String cli,
            @NonNull String acc,
            @NonNull String sess,
            @NonNull String coll,
            @Nullable Query query,
            @NonNull MessagePush msg) {
        this.app = app;
        this.cli = cli;
        this.acc = acc;
        this.sess = sess;
        this.coll = coll;
        this.query = query;
        this.msg = msg;
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

    public MessagePush getMsg() {
        return msg;
    }
}
