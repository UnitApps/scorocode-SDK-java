package ru.profit_group.scorocode_sdk.Requests.user;

import java.util.HashMap;

import ru.profit_group.scorocode_sdk.scorocode_objects.ScorocodeSdkStateHolder;

/**
 * Created by Peter Staranchuk on 5/10/16
 */
public class RequestRegisterUser {
    private String app;
    private String cli;
    private String username;
    private String email;
    private String password;
    private HashMap<String, String> doc;


    public RequestRegisterUser(ScorocodeSdkStateHolder stateHolder, String username, String email, String password, HashMap<String, String>  doc) {
        this.app = stateHolder.getApplicationId();
        this.cli = stateHolder.getClientKey();
        this.username = username;
        this.email = email;
        this.password = password;
        this.doc = doc;
    }

    public RequestRegisterUser(ScorocodeSdkStateHolder stateHolder, String username, String email, String password) {
        this(stateHolder, username, email, password, null);
    }

    public String getApp() {
        return app;
    }

    public String getCli() {
        return cli;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public HashMap<String, String>  getDoc() {
        if(doc == null) {
            return new HashMap<>();
        }
        return doc;
    }
}
