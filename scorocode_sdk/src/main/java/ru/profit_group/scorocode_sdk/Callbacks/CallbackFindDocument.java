package ru.profit_group.scorocode_sdk.Callbacks;

import java.util.HashMap;
import java.util.List;

import ru.profit_group.scorocode_sdk.scorocode_objects.DocumentInfo;

/**
 * Created by Peter Staranchuk on 10/12/16
 */

public interface CallbackFindDocument {
    void onDocumentFound(List<DocumentInfo> documentInfos);
    void onDocumentNotFound(String errorCode, String errorMessage);
}
