package ru.profit_group.scorocode_sdk.Requests.data;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import ru.profit_group.scorocode_sdk.scorocode_objects.Query;
import ru.profit_group.scorocode_sdk.scorocode_objects.ScorocodeSdkStateHolder;


/**
 * Created by Peter Staranchuk on 5/10/16
 */
public class RequestCount {
    private String app;
    private String cli;
    private String acc;
    private String sess;
    private String coll;
    private Query query;

    public RequestCount(
            @NonNull ScorocodeSdkStateHolder stateHolder,
            @NonNull String coll,
            @Nullable Query query) {
        this.app = stateHolder.getApplicationId();
        this.cli = stateHolder.getClientKey();
        this.acc = stateHolder.getMasterKey();
        this.sess = stateHolder.getSessionId();
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
