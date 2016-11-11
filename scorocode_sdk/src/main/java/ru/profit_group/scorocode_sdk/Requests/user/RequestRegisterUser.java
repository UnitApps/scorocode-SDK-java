package ru.profit_group.scorocode_sdk.Requests.user;

import java.util.HashMap;

import ru.profit_group.scorocode_sdk.scorocode_objects.DocumentInfo;
import ru.profit_group.scorocode_sdk.scorocode_objects.ScorocodeSdkStateHolder;

/**
 * Created by Peter Staranchuk on 5/10/16
 */
public class RequestRegisterUser {
    private String app;
    private String cli;
    private String acc;
    private String username;
    private String email;
    private String password;
    private HashMap<String, Object> doc;


    public RequestRegisterUser(ScorocodeSdkStateHolder stateHolder, String username, String email, String password, DocumentInfo  doc) {
        this.app = stateHolder.getApplicationId();
        this.cli = stateHolder.getClientKey();
        this.acc = stateHolder.getMasterKey();
        this.email = email;
        this.username = username;
        this.password = password;
        if(doc != null) {
            this.doc = doc.getContent();
        }
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

    public DocumentInfo getDocumentInfo() {
        if(doc == null) {
            return new DocumentInfo(null);
        }
        return new DocumentInfo(doc);
    }

    public String getAcc() {
        return acc;
    }
}
