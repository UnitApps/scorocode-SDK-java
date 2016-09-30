package ru.profit_group.scorocodesdk.Responses;

import java.util.HashMap;

import ru.profit_group.scorocodesdk.Responses.ResponseUpdate;

/**
 * Created by peter on 25/09/16.
 */
public class ResponseUpdateById {
    private boolean error;
    private String errCode;
    private String errMsg;
    private Result result;

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
