package ru.profit_group.scorocode_sdk.Callbacks;

import ru.profit_group.scorocode_sdk.Responses.data.ResponseUpdate;

/**
 * Created by Peter Staranchuk on 10/12/16
 */

public interface CallbackUpdateDocument {
    void onUpdateSucceed(ResponseUpdate responseUpdate);
    void onUpdateFailed(String errorCode, String errorMessage);
}
