package ru.profit_group.scorocode_sdk.scorocode_objects;

import ru.profit_group.scorocode_sdk.Callbacks.CallbackLoginUser;
import ru.profit_group.scorocode_sdk.Callbacks.CallbackLogoutUser;
import ru.profit_group.scorocode_sdk.Callbacks.CallbackRegisterUser;
import ru.profit_group.scorocode_sdk.Responses.user.ResponseLogin;
import ru.profit_group.scorocode_sdk.ScorocodeSdk;

/**
 * Created by Peter Staranchuk on 10/7/16
 */

public class User extends Document{

    public User() {
        super("users");
    }

    public void login(String email, String password, final CallbackLoginUser callback) {
        ScorocodeSdk.loginUser(email, password, new CallbackLoginUser() {
            @Override
            public void onLoginSucceed(ResponseLogin responseLogin) {
                if(responseLogin != null && responseLogin.getResult() != null && responseLogin.getResult().getUserInfo() != null) {
                    documentContent = responseLogin.getResult().getUserInfo();
                    if(documentContent != null) {
                        documentId = documentContent.getId();
                    }
                }
                callback.onLoginSucceed(responseLogin);
            }

            @Override
            public void onLoginFailed(String errorCode, String errorMessage) {
                callback.onLoginFailed(errorCode, errorMessage);
            }
        });
    }

    public void logout(final CallbackLogoutUser callback) {
        ScorocodeSdk.logoutUser(new CallbackLogoutUser() {
            @Override
            public void onLogoutSucceed() {
                documentId = null;
                documentContent = null;
                callback.onLogoutSucceed();
            }

            @Override
            public void onLogoutFailed(String errorCode, String errorMessage) {
                callback.onLogoutFailed(errorCode, errorMessage);
            }
        });
    }

    public void register(String username, String email, String password, DocumentInfo documentContent, CallbackRegisterUser callback) {
        ScorocodeSdk.registerUser(username, email, password, documentContent, callback);
    }

    public void register(String username, String email, String password, CallbackRegisterUser callback) {
        register(username, email, password, null, callback);
    }
}
