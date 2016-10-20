package ru.profit_group.scorocode_sdk.Responses.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public class Result {
        private int count;
        private List<String> docs;
//        private Map<String, Object> additionalProperties = new HashMap<String, Object>();

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public List<String> getDocs() {
            return docs;
        }

        public void setDocs(List<String> docs) {
            this.docs = docs;
        }

//        public Map<String, Object> getAdditionalProperties() {
//            return additionalProperties;
//        }

//        public void setAdditionalProperties(Map<String, Object> additionalProperties) {
//            this.additionalProperties = additionalProperties;
//        }
    }
}
