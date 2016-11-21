package ru.profit_group.scorocode_sdk.Requests.user;

import ru.profit_group.scorocode_sdk.scorocode_objects.ScorocodeSdkStateHolder;

/**
 * Created by Peter Staranchuk on 5/10/16
 */
public class RequestLogoutUser {
    private String app;
    private String cli;
    private String sess;

    public RequestLogoutUser(ScorocodeSdkStateHolder stateHolder) {
        this.app = stateHolder.getApplicationId();
        this.cli = stateHolder.getClientKey();
        this.sess = stateHolder.getSessionId();
    }
}
