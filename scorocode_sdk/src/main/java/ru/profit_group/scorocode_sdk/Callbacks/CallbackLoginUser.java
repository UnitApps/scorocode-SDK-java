package ru.profit_group.scorocode_sdk.Callbacks;

import ru.profit_group.scorocode_sdk.Responses.user.ResponseLogin;

/**
 * Created by Peter Staranchuk on 10/12/16
 */

public interface CallbackLoginUser {
    void onLoginSucceed(ResponseLogin responseLogin);
    void onLoginFailed(String errorCode, String errorMessage);
}
