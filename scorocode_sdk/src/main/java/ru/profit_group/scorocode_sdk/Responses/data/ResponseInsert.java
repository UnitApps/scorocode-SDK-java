package ru.profit_group.scorocode_sdk.Responses.data;

import java.util.HashMap;

import ru.profit_group.scorocode_sdk.Responses.ResponseCodes;
import ru.profit_group.scorocode_sdk.scorocode_objects.DocumentInfo;

/**
 * Created by Peter Staranchuk on 5/10/16
 */
public class ResponseInsert extends ResponseCodes {
    private DocumentInfo result;

    public DocumentInfo getResult() {
        return result;
    }

    public void setResult(DocumentInfo result) {
        this.result = result;
    }
}
