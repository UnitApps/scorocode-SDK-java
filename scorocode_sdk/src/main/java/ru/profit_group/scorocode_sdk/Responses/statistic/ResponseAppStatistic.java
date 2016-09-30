package ru.profit_group.scorocode_sdk.Responses.statistic;

import ru.profit_group.scorocode_sdk.Responses.ResponseCodes;

/**
 * Created by Peter Staranchuk on 5/10/16
 */

//POJO object
public class ResponseAppStatistic extends ResponseCodes{
    public Result result;

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