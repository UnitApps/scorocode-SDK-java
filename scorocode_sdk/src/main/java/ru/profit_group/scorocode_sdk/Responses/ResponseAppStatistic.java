package ru.profit_group.scorocode_sdk.Responses;

/**
 * Created by peter on 20/09/16.
 */

//POJO object
public class ResponseAppStatistic {
    public boolean error;
    public Result result;

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

    private class Result {
        public long dataSize;
        public long filesSize;
        public long indexSize;
        public double store;
    }
}