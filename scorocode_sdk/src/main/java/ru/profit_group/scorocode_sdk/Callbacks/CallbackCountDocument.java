package ru.profit_group.scorocode_sdk.Callbacks;

import ru.profit_group.scorocode_sdk.Responses.data.ResponseCount;

/**
 * Created by Peter Staranchuk on 10/12/16
 */

public interface CallbackCountDocument {
    void onDocumentsCounted(ResponseCount responseCount);
    void onCountFailed(String errorCode, String errorMessage);
}
