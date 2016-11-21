package ru.profit_group.scorocode_sdk.Requests.files;

import android.support.annotation.NonNull;

import ru.profit_group.scorocode_sdk.scorocode_objects.ScorocodeSdkStateHolder;

/**
 * Created by Peter Staranchuk on 5/10/2016
 */

public class RequestUpload {
    private String app;
    private String cli;
    private String acc;
    private String sess;
    private String coll;
    private String docId;
    private String field;
    private String file;
    private String content; //in base 64 format

    public RequestUpload(
            @NonNull ScorocodeSdkStateHolder stateHolder,
            @NonNull String coll,
            @NonNull String docId,
            @NonNull String field,
            @NonNull String file,
            @NonNull String content) {

        this.app = stateHolder.getApplicationId();
        this.cli = stateHolder.getClientKey();
        this.acc = stateHolder.getMasterOrFileKey();
        this.sess = stateHolder.getSessionId();
        this.coll = coll;
        this.docId = docId;
        this.field = field;
        this.file = file;
        this.content = content;
    }
}
