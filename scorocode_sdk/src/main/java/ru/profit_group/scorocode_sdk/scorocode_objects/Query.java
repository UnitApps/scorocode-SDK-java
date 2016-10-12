package ru.profit_group.scorocode_sdk.scorocode_objects;

import android.support.annotation.NonNull;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.List;

import retrofit2.Callback;
import ru.profit_group.scorocode_sdk.Callbacks.CallbackCountDocument;
import ru.profit_group.scorocode_sdk.Callbacks.CallbackFindDocument;
import ru.profit_group.scorocode_sdk.Callbacks.CallbackRemoveDocument;
import ru.profit_group.scorocode_sdk.Callbacks.CallbackUpdateDocument;
import ru.profit_group.scorocode_sdk.Responses.data.ResponseCount;
import ru.profit_group.scorocode_sdk.Responses.data.ResponseRemove;
import ru.profit_group.scorocode_sdk.Responses.data.ResponseUpdate;
import ru.profit_group.scorocode_sdk.ScorocodeSdk;

/**
 * Created by Peter Staranchuk on 25/09/16
 */
public class Query extends HashMap<String, HashMap<String,Object>> {

    private String collectionName;
    private Integer limit;
    private Integer skip;
    private Sort sort;
    private List<String> fieldIds;

    public Query(String collectionName) {
        this.collectionName = collectionName;
        this.sort = new Sort();
    }

    public static Query getSimpleQuery(String field, String operator, String value) {
        HashMap<String, Object> record = getRecord(value, operator);

        Query query = new Query(null);
        query.put(field, record);
        return query;
    }


    public Query(String field, String operator, String value) {
        HashMap<String, Object> query = getRecord(value, operator);

        this.put(field, query);
    }

    public void findDocuments(CallbackFindDocument callback) {
        ScorocodeSdk.findDocument(collectionName, this, sort, fieldIds, limit, skip, callback);
    }

    public void countDocuments(CallbackCountDocument callback) {
        ScorocodeSdk.getDocumentsCount(collectionName, this, callback);
    }

    public void updateDocument(Update update, CallbackUpdateDocument callback) {
        HashMap<String, HashMap<String,Object>> doc = update.getUpdateInfo();
        ScorocodeSdk.updateDocument(collectionName, this, doc, limit, callback);
    }

    public void removeDocument(CallbackRemoveDocument callback) {
        ScorocodeSdk.removeDocument(collectionName, this, limit, callback);
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public void setSkip(Integer skip) {
        this.skip = skip;
    }

    public void setPage(Integer page) {
        if(page > 0) {
            skip = (page - 1) * limit;
        }
    }

    public Query equalTo(String field, Object value) {
        this.put(field, getRecord(value, "$eq"));
        return this;
    }

    public Query notEqualTo(String field, Object value) {
        this.put(field, getRecord(value, "$ne"));
        return this;
    }

    public Query containedIn(String field, Object value) {
        this.put(field, getRecord(value, "$in"));
        return this;
    }

    public Query containsAll(String field, List<String> values) {
        for (String value : values) {
            this.put(field, getRecord(value, "$in"));
        }
        return this;
    }

    public Query notContainedIn(String field, String value) {
        this.put(field, getRecord(value, "$nin"));
        return this;
    }

    public Query greaterThan(String field, Integer value) {
        this.put(field, getRecord(value, "$gt"));
        return this;
    }

    public Query greaterThenOrEqualTo(String field, Integer value) {
        this.put(field, getRecord(value, "$gte"));
        return this;
    }

    public Query lessThan(String field, Integer value) {
        this.put(field, getRecord(value, "$lt"));
        return this;
    }

    public Query lessThanOrEqualTo(String field, Integer value) {
        this.put(field, getRecord(value, "$lte"));
        return this;
    }

    public Query exists(String field) {
        this.put(field, getRecord(true, "$exists"));
        return this;
    }

    public Query doesNotExist(String field) {
        this.put(field, getRecord(false, "$exists"));
        return this;
    }

    public Query contains(String field, String regEx, String options) {
        HashMap<String, Object> operationMap = new HashMap<>();
        operationMap.put("$regex", regEx);
        operationMap.put("$options", options);

        this.put(field, operationMap);
        return this;
    }

    public Query startsWith(String field, String regEx, String options) {
        HashMap<String, Object> operationMap = new HashMap<>();
        operationMap.put("$regex", "^" + regEx);
        operationMap.put("$options", options);

        this.put(field, operationMap);
        return this;
    }

    public Query endsWith(String field, String regEx, String options) {
        HashMap<String, Object> operationMap = new HashMap<>();
        operationMap.put("$regex", regEx + "$");
        operationMap.put("$options", options);

        this.put(field, operationMap);
        return this;
    }

    public Query or(String field, Query query) {
        this.put(field, getRecord(query, "$or"));
        return this;
    }

    public Query and(String field, Query query) {
        this.put(field, getRecord(query, "$and"));
        return this;
    }

    @NonNull
    private static HashMap<String, Object> getRecord(Object value, String operator) {
        HashMap<String, Object> record = new HashMap<>();
        record.put(operator, value);
        return record;
    }

    public void raw(String json) {
        Gson gson = new Gson();
        Query query = gson.fromJson(json, Query.class);
        reset();

        this.putAll(query);
    }

    public void reset() {
        this.clear();
    }

    public void ascending(String field) {
        sort.put(field, 1);
    }

    public void descending(String field) {
        sort.put(field, -1);
    }

    public void setFieldsForSearch(List<String> fields) {
        fieldIds = fields;
    }
}
