package ru.profit_group.scorocode_sdk.Requests.messages;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.Map;

import ru.profit_group.scorocode_sdk.ScorocodeSdk;
import ru.profit_group.scorocode_sdk.scorocode_objects.Query;
import ru.profit_group.scorocode_sdk.scorocode_objects.QueryInfo;
import ru.profit_group.scorocode_sdk.scorocode_objects.ScorocodeSdkStateHolder;

/**
 * Created by Peter Staranchuk on 5/10/2016
 */

public class RequestSendSms {
    private String app;
    private String cli;
    private String acc;
    private String sess;
    private String coll;
    private Map<String, Object> query;
    private MessageSms msg;

    public RequestSendSms(
            @NonNull ScorocodeSdkStateHolder stateHolder,
            @NonNull String coll,
            @Nullable Query query,
            @NonNull MessageSms msg) {

        this.app = stateHolder.getApplicationId();
        this.cli = stateHolder.getClientKey();
        this.acc = stateHolder.getMasterOrMessageKey();
        this.sess = stateHolder.getSessionId();
        this.coll = coll;
        if(query != null) {
            this.query = query.getQueryInfo().getInfo();
        }
        this.msg = msg;
    }
}
