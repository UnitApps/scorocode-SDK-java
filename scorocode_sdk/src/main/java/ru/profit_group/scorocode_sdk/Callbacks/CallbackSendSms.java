package ru.profit_group.scorocode_sdk.Callbacks;

/**
 * Created by Peter Staranchuk on 10/12/16
 */

public interface CallbackSendSms {
    void onSmsSended();
    void onSmsSendFailed(String errorCode, String errorMessage);
}
