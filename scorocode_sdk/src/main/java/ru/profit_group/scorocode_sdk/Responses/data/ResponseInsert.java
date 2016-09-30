package ru.profit_group.scorocode_sdk.Responses.data;

import java.util.HashMap;

import ru.profit_group.scorocode_sdk.Responses.ResponseCodes;

/**
 * Created by Peter Staranchuk on 5/10/16
 */
public class ResponseInsert extends ResponseCodes {
    private HashMap<String, String> result;

    public HashMap<String, String> getResult() {
        return result;
    }

    public void setResult(HashMap<String, String> result) {
        this.result = result;
    }
}
