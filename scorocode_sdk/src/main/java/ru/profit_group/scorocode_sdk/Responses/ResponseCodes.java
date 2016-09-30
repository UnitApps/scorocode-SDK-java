package ru.profit_group.scorocode_sdk.Responses;

/**
 * Created by Peter Staranchuk on 5/10/16
 */
public class ResponseCodes {
    protected boolean error;
    protected String errCode;
    protected String errMsg;

    public boolean isError() {
        return error;
    }

    public String getErrCode() {
        return errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

}
