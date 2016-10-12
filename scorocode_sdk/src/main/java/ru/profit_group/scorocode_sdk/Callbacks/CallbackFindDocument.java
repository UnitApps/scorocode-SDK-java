package ru.profit_group.scorocode_sdk.Callbacks;

import java.util.List;

/**
 * Created by Peter Staranchuk on 10/12/16
 */

public interface CallbackFindDocument {
    void onDocumentFound(List<String> documentsIds);
    void onDocumentNotFound(String errorCode, String errorMessage);
}
