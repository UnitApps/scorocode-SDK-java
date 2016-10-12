package ru.profit_group.scorocode_sdk.Callbacks;

import ru.profit_group.scorocode_sdk.Requests.data.RequestUpdateById;
import ru.profit_group.scorocode_sdk.Responses.data.ResponseUpdateById;

/**
 * Created by Peter Staranchuk on 10/12/16
 */

public interface CallbackUpdateDocumentById {
    void onUpdateByIdSucceed(ResponseUpdateById requestUpdateById);
    void onUpdateByIdFailed(String errorCode, String errorMessage);
}
