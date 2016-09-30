package ru.profit_group.scorocode_sdk.Responses.data;

import ru.profit_group.scorocode_sdk.Responses.ResponseCodes;

/**
 * Created by Peter Staranchuk on 5/10/16
 */

public class ResponseUpdate extends ResponseCodes{
    private Result result;

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
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
