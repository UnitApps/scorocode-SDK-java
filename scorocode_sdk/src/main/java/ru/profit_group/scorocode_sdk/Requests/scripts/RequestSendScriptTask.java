package ru.profit_group.scorocode_sdk.Requests.scripts;

import android.support.annotation.NonNull;

import java.util.HashMap;

/**
 * Created by Peter Staranchuk on 5/10/2016
 */

public class RequestSendScriptTask {
    private String app;
    private String cli;
    private String acc;
    private String sess;
    private String script;
    private HashMap<String,String> pool;

    public RequestSendScriptTask(
            @NonNull String applicationId,
            @NonNull String clientId,
            @NonNull String accessKey,
            @NonNull String sessionId,
            @NonNull String scriptId,
            @NonNull HashMap<String, String> dataPoolForScript) {
        this.app = applicationId;
        this.cli = clientId;
        this.acc = accessKey;
        this.sess = sessionId;
        this.script = scriptId;
        this.pool = dataPoolForScript;
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

    public String getScript() {
        return script;
    }

    public HashMap<String, String> getPool() {
        return pool;
    }
}
