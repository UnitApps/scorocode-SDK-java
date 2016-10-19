package ru.profit_group.scorocode_sdk.Requests.scripts;

import android.support.annotation.NonNull;

import java.util.HashMap;

import ru.profit_group.scorocode_sdk.scorocode_objects.ScorocodeSdkStateHolder;

/**
 * Created by Peter Staranchuk on 5/10/2016
 */

public class RequestSendScriptTask {
    private String app;
    private String cli;
    private String acc;
    private String sess;
    private String script;
    private Object pool;

    public RequestSendScriptTask(
            @NonNull ScorocodeSdkStateHolder stateHolder,
            @NonNull String scriptId,
            @NonNull Object dataPoolForScript) {
        this.app = stateHolder.getApplicationId();
        this.cli = stateHolder.getClientKey();
        this.acc = stateHolder.getMasterOrScriptKey();
        this.sess = stateHolder.getSessionId();
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

    public Object getPool() {
        return pool;
    }
}
