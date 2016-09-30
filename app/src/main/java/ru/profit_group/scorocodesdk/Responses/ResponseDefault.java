package ru.profit_group.scorocodesdk.Responses;

/**
 * Created by peter on 21/09/16.
 */
public class ResponseDefault {
    boolean error;
    String errCode;
    String errMsg;

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
