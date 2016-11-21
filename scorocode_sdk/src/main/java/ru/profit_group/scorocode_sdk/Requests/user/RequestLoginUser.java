package ru.profit_group.scorocode_sdk.Requests.user;

import ru.profit_group.scorocode_sdk.scorocode_objects.ScorocodeSdkStateHolder;

/**
 * Created by Peter Staranchuk on 5/10/16
 */
public class RequestLoginUser {
    private String app;
    private String cli;
    private String email;
    private String password;

    public RequestLoginUser(ScorocodeSdkStateHolder stateHolder, String email, String password) {
        this.app = stateHolder.getApplicationId();
        this.cli = stateHolder.getClientKey();
        this.email = email;
        this.password = password;
    }
}
