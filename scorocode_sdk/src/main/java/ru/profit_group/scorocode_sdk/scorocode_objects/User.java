package ru.profit_group.scorocode_sdk.scorocode_objects;

import java.util.HashMap;

import retrofit2.Callback;
import ru.profit_group.scorocode_sdk.Responses.ResponseCodes;
import ru.profit_group.scorocode_sdk.Responses.user.ResponseLogin;
import ru.profit_group.scorocode_sdk.ScorocodeSdk;

/**
 * Created by Peter Staranchuk on 10/7/16
 */

public class User extends Document{

    public User() {
        super("users");
    }

    public void login(String email, String password, Callback<ResponseLogin> callback) {
        ScorocodeSdk.loginUser(ScorocodeSdk.getAppId(), ScorocodeSdk.getClientKey(), email, password, callback);
    }

    public void logout(Callback<ResponseCodes> callback) {
        ScorocodeSdk.logoutUser(ScorocodeSdk.getAppId(), ScorocodeSdk.getClientKey(), ScorocodeSdk.getSessionId(), callback);
    }

    public void register(String username, String email, String password, HashMap<String,String> doc, Callback<ResponseCodes> callback) {
        ScorocodeSdk.registerUser(ScorocodeSdk.getAppId(), ScorocodeSdk.getClientKey(), username, email, password, doc, callback);
    }

    public void register(String username, String email, String password, Callback<ResponseCodes> callback) {
        register(username, email, password, null, callback);
    }
}
