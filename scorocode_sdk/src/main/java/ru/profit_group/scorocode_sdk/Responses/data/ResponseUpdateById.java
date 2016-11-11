package ru.profit_group.scorocode_sdk.Responses.data;

import java.util.HashMap;

import ru.profit_group.scorocode_sdk.Responses.ResponseCodes;
import ru.profit_group.scorocode_sdk.scorocode_objects.DocumentInfo;

/**
 * Created by Peter Staranchuk on 5/10/16
 */
public class ResponseUpdateById extends ResponseCodes{
    private HashMap<String, Object> result;

    public DocumentInfo getResult() {
        return new DocumentInfo(result);
    }

    public void setResult(HashMap<String, Object> result) {
        this.result = result;
    }

}
