package ru.profit_group.scorocode_sdk.Requests.data;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.HashMap;
import java.util.Map;

import ru.profit_group.scorocode_sdk.scorocode_objects.QueryInfo;
import ru.profit_group.scorocode_sdk.scorocode_objects.ScorocodeSdkStateHolder;
import ru.profit_group.scorocode_sdk.scorocode_objects.UpdateInfo;

/**
 * Created by Peter Staranchuk on 5/10/16
 */
public class RequestUpdateById {
    private String app;
    private String cli;
    private String acc;
    private String sess;
    private String coll;
    private Map<String, Object> query;
    private Map<String, HashMap<String,Object>> doc;

    public RequestUpdateById(
            @NonNull ScorocodeSdkStateHolder stateHolder,
            @NonNull String collectionName,
            @NonNull QueryInfo query,
            @NonNull UpdateInfo doc) {
        this.app = stateHolder.getApplicationId();
        this.cli = stateHolder.getClientKey();
        this.acc = stateHolder.getMasterKey();
        this.sess = stateHolder.getSessionId();
        this.coll = collectionName;
        this.query = query.getInfo();
        this.doc = doc.getInfo();
    }
}
