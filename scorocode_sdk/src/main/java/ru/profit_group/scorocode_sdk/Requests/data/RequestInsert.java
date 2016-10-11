package ru.profit_group.scorocode_sdk.Requests.data;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.HashMap;

import ru.profit_group.scorocode_sdk.scorocode_objects.ScorocodeSdkStateHolder;

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
            @NonNull ScorocodeSdkStateHolder stateHolder,
            @NonNull String collectionName,
            @Nullable HashMap<String, String> doc) {
        this.app = stateHolder.getApplicationId();
        this.cli = stateHolder.getClientKey();
        this.acc = stateHolder.getMasterKey();
        this.sess = stateHolder.getSessionId();
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
