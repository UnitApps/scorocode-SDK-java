package ru.profit_group.scorocode_sdk.Responses;

import java.util.HashMap;

/**
 * Created by peter on 25/09/16.
 */
public class ResponseInsert {
    private boolean error;
    private String errCode;
    private String errMsg;
    private HashMap<String, String> result;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public HashMap<String, String> getResult() {
        return result;
    }

    public void setResult(HashMap<String, String> result) {
        this.result = result;
    }
}
