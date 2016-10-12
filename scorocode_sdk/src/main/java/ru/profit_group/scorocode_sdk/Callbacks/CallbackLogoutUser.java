package ru.profit_group.scorocode_sdk.Callbacks;

/**
 * Created by Peter Staranchuk on 10/12/16
 */

public interface CallbackLogoutUser {
    void onLogoutSucceed();
    void onLogoutFailed(String errorCode, String errorMessage);
}
