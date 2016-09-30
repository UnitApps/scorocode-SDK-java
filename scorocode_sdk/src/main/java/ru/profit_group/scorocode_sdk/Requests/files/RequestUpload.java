package ru.profit_group.scorocode_sdk.Requests.files;

import android.support.annotation.NonNull;

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
            @NonNull String app,
            @NonNull String cli,
            @NonNull String acc,
            @NonNull String sess,
            @NonNull String coll,
            @NonNull String docId,
            @NonNull String field,
            @NonNull String file,
            @NonNull String content) {
        this.app = app;
        this.cli = cli;
        this.acc = acc;
        this.sess = sess;
        this.coll = coll;
        this.docId = docId;
        this.field = field;
        this.file = file;
        this.content = content;
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

    public String getContent() {
        return content;
    }
}
