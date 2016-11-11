package ru.profit_group.scorocode_sdk.Callbacks;

/**
 * Created by Peter Staranchuk on 11/7/16
 */

public interface CallbackGetFile {
    void onSucceed(String fileContent);
    void onFailed(String errorCode, String errorMessage);
}
