package ru.profit_group.scorocodesdk.Requests;

/**
 * Created by peter staranchuk on 21/09/16.
 */
public class RequestLoginUser {
    private String app;
    private String cli;
    private String email;
    private String password;

    public RequestLoginUser(String app, String cli, String email, String password) {
        this.app = app;
        this.cli = cli;
        this.email = email;
        this.password = password;
    }

    public String getApp() {
        return app;
    }

    public String getCli() {
        return cli;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
