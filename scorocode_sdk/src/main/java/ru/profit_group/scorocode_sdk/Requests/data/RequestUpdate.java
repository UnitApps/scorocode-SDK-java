package ru.profit_group.scorocode_sdk.Requests.data;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.HashMap;
import java.util.Map;

import ru.profit_group.scorocode_sdk.scorocode_objects.Query;
import ru.profit_group.scorocode_sdk.scorocode_objects.QueryInfo;
import ru.profit_group.scorocode_sdk.scorocode_objects.ScorocodeSdkStateHolder;
import ru.profit_group.scorocode_sdk.scorocode_objects.UpdateInfo;

/**
 * Created by Peter Staranchuk on 5/10/16
 */
public class RequestUpdate {
    private String app;
    private String cli;
    private String acc;
    private String sess;
    private String coll;
    private Map<String, Object> query;
    private Map<String, HashMap<String, Object>> doc;
    private Integer limit;

    public RequestUpdate(
            @NonNull ScorocodeSdkStateHolder stateHolder,
            @NonNull String collectionName,
            @Nullable Query query,
            @NonNull UpdateInfo doc,
            @Nullable Integer limit) {

        this.app = stateHolder.getApplicationId();
        this.cli = stateHolder.getClientKey();
        this.acc = stateHolder.getMasterKey();
        this.sess = stateHolder.getSessionId();
        this.coll = collectionName;
        if(query != null) {
            this.query = query.getQueryInfo().getInfo();
        }
        this.doc = doc.getInfo();
        this.limit = limit;
    }
}
