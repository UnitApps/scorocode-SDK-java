package ru.profit_group.scorocode_sdk.Callbacks;

import ru.profit_group.scorocode_sdk.Responses.data.ResponseRemove;

/**
 * Created by Peter Staranchuk on 10/12/16
 */

public interface CallbackRemoveDocument {
    void onRemoveSucceed(ResponseRemove responseRemove);
    void onRemoveFailed(String errorCode, String errorMessage);
}
