package ru.profit_group.scorocode_sdk.Requests.files;

import android.support.annotation.NonNull;

import ru.profit_group.scorocode_sdk.scorocode_objects.ScorocodeSdkStateHolder;

/**
 * Created by Peter Staranchuk on 5/10/2016
 */

public class RequestFile {
    private String app;
    private String cli;
    private String acc;
    private String sess;
    private String coll;
    private String docId;
    private String field;
    private String file;

    public RequestFile(
            @NonNull ScorocodeSdkStateHolder stateHolder,
            @NonNull String coll,
            @NonNull String docId,
            @NonNull String field,
            @NonNull String file) {

        this.app = stateHolder.getApplicationId();
        this.cli = stateHolder.getClientKey();
        this.acc = stateHolder.getMasterOrFileKey();
        this.sess = stateHolder.getSessionId();
        this.coll = coll;
        this.docId = docId;
        this.field = field;
        this.file = file;
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

    public String getDocId() {
        return docId;
    }

    public String getField() {
        return field;
    }

    public String getFile() {
        return file;
    }
}
