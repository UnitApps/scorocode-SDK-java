package ru.profit_group.scorocode_sdk.Responses;

/**
 * Created by peter on 25/09/16.
 */

public class ResponseUpdate {
    private boolean error;
    private Result result;
    private String errCode;
    private String errMsg;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
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

    private class Result {
        private int count;
        private int[] docIds;

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public int[] getDocIds() {
            return docIds;
        }

        public void setDocIds(int[] docIds) {
            this.docIds = docIds;
        }
    }
}
