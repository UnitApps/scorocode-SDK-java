package ru.profit_group.scorocode_sdk.Responses.data;

import java.util.HashMap;

import ru.profit_group.scorocode_sdk.Responses.ResponseCodes;

/**
 * Created by Peter Staranchuk on 5/10/16
 */
public class ResponseUpdateById extends ResponseCodes{
    private Result result;

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    private class Result {
        HashMap<String, String> doc;
    }
}
