package ru.profit_group.scorocode_sdk.Callbacks;

import ru.profit_group.scorocode_sdk.scorocode_objects.DocumentInfo;

/**
 * Created by Peter Staranchuk on 10/21/16
 */

public interface CallbackGetDocumentById {
    void onDocumentFound(DocumentInfo documentInfo);
    void onDocumentNotFound(String errorCode, String errorMessage);
}
