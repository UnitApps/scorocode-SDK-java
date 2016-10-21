package ru.profit_group.scorocode_sdk.Requests.messages;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import ru.profit_group.scorocode_sdk.scorocode_objects.Query;
import ru.profit_group.scorocode_sdk.scorocode_objects.QueryInfo;
import ru.profit_group.scorocode_sdk.scorocode_objects.ScorocodeSdkStateHolder;

/**
 * Created by Peter Staranchuk on 5/10/2016
 */

public class RequestSendEmail {
    private String app;
    private String cli;
    private String acc;
    private String sess;
    private String coll;
    private QueryInfo query;
    private MessageEmail msg;

    public RequestSendEmail(
            @NonNull ScorocodeSdkStateHolder stateHolder,
            @NonNull String coll,
            @Nullable Query query,
            @NonNull MessageEmail msg) {

        this.app = stateHolder.getApplicationId();
        this.cli = stateHolder.getClientKey();
        this.acc = stateHolder.getMasterOrMessageKey();
        this.sess = stateHolder.getSessionId();
        this.coll = coll;
        if (query != null) {
            this.query = query.getQueryInfo();
        }
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

    public QueryInfo getQuery() {
        return query;
    }

    public MessageEmail getMsg() {
        return msg;
    }
}
