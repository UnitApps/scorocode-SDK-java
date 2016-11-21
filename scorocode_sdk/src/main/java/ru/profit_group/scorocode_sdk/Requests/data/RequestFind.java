package ru.profit_group.scorocode_sdk.Requests.data;


import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.List;
import java.util.Map;

import ru.profit_group.scorocode_sdk.scorocode_objects.Query;
import ru.profit_group.scorocode_sdk.scorocode_objects.ScorocodeSdkStateHolder;
import ru.profit_group.scorocode_sdk.scorocode_objects.SortInfo;

/**
 * Created by Peter Staranchuk on 5/10/16
 */
public class RequestFind {
    private String app;
    private String cli;
    private String acc;
    private String sess;
    private String coll;
    private Map<String, Object> query;
    private Map<String, Integer> sort;
    private List<String> fields;
    private Integer limit;
    private Integer skip;

    public RequestFind(
            @NonNull ScorocodeSdkStateHolder stateHolder,
            @NonNull String coll,
            @Nullable Query query,
            @Nullable SortInfo sort,
            @Nullable List<String> fields,
            @Nullable Integer limit,
            @Nullable Integer skip) {

        this.app = stateHolder.getApplicationId();
        this.cli = stateHolder.getClientKey();
        this.acc = stateHolder.getMasterKey();
        this.sess = stateHolder.getSessionId();
        this.coll = coll;
        if(query != null) {
            this.query = query.getQueryInfo().getInfo();
        }
        if(sort != null) {
            this.sort = sort.getInfo();
        }
        this.fields = fields;
        this.limit = limit;
        this.skip = skip;
    }
}
