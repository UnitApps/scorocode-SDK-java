package ru.profit_group.scorocode_sdk.Responses.data;

import java.util.HashMap;

import ru.profit_group.scorocode_sdk.Responses.ResponseCodes;

/**
 * Created by Peter Staranchuk on 5/10/16
 */
public class ResponseUpdateById extends ResponseCodes{
    private  HashMap<String, Object> result;

    public  HashMap<String, Object> getResult() {
        return result;
    }

    public void setResult( HashMap<String, Object> result) {
        this.result = result;
    }

//    private class Result {
//        HashMap<String, Object> docs;
//    }
}
