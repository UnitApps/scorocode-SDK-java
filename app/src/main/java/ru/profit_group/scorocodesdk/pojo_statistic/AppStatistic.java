package ru.profit_group.scorocodesdk.pojo_statistic;

/**
 * Created by peter on 20/09/16.
 */

//POJO object
public class AppStatistic {
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
}