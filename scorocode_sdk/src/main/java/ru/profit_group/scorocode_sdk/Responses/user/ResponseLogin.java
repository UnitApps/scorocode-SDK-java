package ru.profit_group.scorocode_sdk.Responses.user;

import java.util.HashMap;

import ru.profit_group.scorocode_sdk.Responses.ResponseCodes;

/**
 * Created by Peter Staranchuk on 5/10/16
 */
public class ResponseLogin extends ResponseCodes {
    private Result result;

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    private class Result {
        String sessionId;
        HashMap<String, String> user;
    }
}
