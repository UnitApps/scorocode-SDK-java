package ru.profit_group.scorocode_sdk.Callbacks;

import ru.profit_group.scorocode_sdk.Responses.data.ResponseInsert;

/**
 * Created by Peter Staranchuk on 10/12/16
 */

public interface CallbackInsert {
    void onInsertSucceed(ResponseInsert responseInsert);
    void onInsertFailed(String errorCode, String errorMessage);
}
