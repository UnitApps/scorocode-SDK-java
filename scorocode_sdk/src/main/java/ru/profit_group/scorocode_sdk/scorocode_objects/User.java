package ru.profit_group.scorocode_sdk.scorocode_objects;

import java.util.HashMap;

import ru.profit_group.scorocode_sdk.Callbacks.CallbackLoginUser;
import ru.profit_group.scorocode_sdk.Callbacks.CallbackLogoutUser;
import ru.profit_group.scorocode_sdk.Callbacks.CallbackRegisterUser;
import ru.profit_group.scorocode_sdk.ScorocodeSdk;

/**
 * Created by Peter Staranchuk on 10/7/16
 */

public class User extends Document{

    public User() {
        super("users");
    }

    public void login(String email, String password, CallbackLoginUser callback) {
        ScorocodeSdk.loginUser(email, password, callback);
    }

    public void logout(CallbackLogoutUser callback) {
        ScorocodeSdk.logoutUser(callback);
    }

    public void register(String username, String email, String password, HashMap<String,String> documentContent, CallbackRegisterUser callback) {
        ScorocodeSdk.registerUser(username, email, password, documentContent, callback);
    }

    public void register(String username, String email, String password, CallbackRegisterUser callback) {
        register(username, email, password, null, callback);
    }
}
