package ru.profit_group.scorocode_sdk.scorocode_objects;

import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.internal.LinkedHashTreeMap;
import com.google.gson.internal.LinkedTreeMap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
public class Query  {

    private String collectionName;
    private Integer limit;
    private Integer skip;
    private Sort sort;
    private List<String> fieldIds;
    private QueryInfo queryInfo;

    public Query(String collectionName) {
        this.collectionName = collectionName;
        this.sort = new Sort();
        this.queryInfo = new QueryInfo();
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
        addQueryRule(field, "$eq", value);
        return this;
    }

    public Query notEqualTo(String field, Object value) {
        addQueryRule(field, "$ne", value);
        return this;
    }

    public Query containedIn(String field, List<Object> values) {
        addQueryRule(field, "$in", values);
        return this;
    }

    public Query containsAll(String field, List<Object> values) {
            addQueryRule(field, "$all", values);
        return this;
    }

    public Query notContainedIn(String field, List<Object> values) {
        addQueryRule(field, "$nin", values);
        return this;
    }

    public Query greaterThan(String field, Integer value) {
        addQueryRule(field, "$gt", value);
        return this;
    }

    public Query greaterThenOrEqualTo(String field, Integer value) {
        addQueryRule(field, "$gte", value);
        return this;
    }

    public Query lessThan(String field, Integer value) {
        addQueryRule(field, "$lt", value);
        return this;
    }

    public Query lessThanOrEqualTo(String field, Integer value) {
        addQueryRule(field, "$lte", value);
        return this;
    }

    public Query exists(String field) {
        addQueryRule(field, "$exists", true);
        return this;
    }

    public Query doesNotExist(String field) {
        addQueryRule(field, "$exists", false);
        return this;
    }

    public Query contains(String field, String regEx, RegexOptions options) {
        //TODO change add
        HashMap<String, Object> operationMap = new HashMap<>();
        operationMap.put("$regex", regEx);
        operationMap.put("$options", options.getRegexOptions());

        queryInfo.put(field, operationMap);
        return this;
    }

    public Query startsWith(String field, String regEx, RegexOptions options) {
        //TODO change add
        HashMap<String, Object> operationMap = new HashMap<>();
        operationMap.put("$regex", "^" + regEx);
        operationMap.put("$options", options.getRegexOptions());

        queryInfo.put(field, operationMap);
        return this;
    }

    public Query endsWith(String field, String regEx, RegexOptions options) {
        //TODO change add

        HashMap<String, Object> operationMap = new HashMap<>();
        operationMap.put("$regex", regEx + "$");
        operationMap.put("$options", options.getRegexOptions());

        queryInfo.put(field, operationMap);
        return this;
    }

    public Query or(String field, Query query) {
        addQueryRule(field, "$or", query);
//        queryInfo.put(field, getRecord(query, "$or"));
        return this;
    }

    public Query and(String field, Query query) {
//        addQueryRule(field, "$and", query);

//        List<HashMap<String, Object>> list = new ArrayList<>();
//        list.add(queryInfo.get(field));
//        list.add(query.getQueryInfo().get(field));
//
//        HashMap<String, List<HashMap<String, List<Object>>>> and = new HashMap<>();
//        and.put("$and", list);

//        queryInfo.put(field, and);


//        Map<String, Object> query1= queryInfo.get(field);
//        Map<String, Object> query2= query.getQueryInfo().get(field);
//        List<HashMap<String, Object>> queries = new ArrayList<>();
//        queries.add(query1);
//        queries.add(query2);

//        addQueryRule(field, "$and", queries);

//        queryInfo.put("$and", )
        String json = "{ \"$and\": [ { \"price\": { \"$ne\": 1.99 } }, { \"price\": { \"$exists\": true } } ] }";
        Gson gson = new Gson();
        Object o = gson.fromJson(json, Object.class);

        Map<String, List<Map<String, Object>>> mapMap = new LinkedTreeMap<>();

        List<Map<String, Object>> list = new ArrayList<>();
//        list.add(query1);
//        list.add(query2);

        mapMap.put("$and", list);
//        addQueryRule(field, "$and", list);
//        queryInfo.put("$and", )
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
        QueryInfo query = gson.fromJson(json, QueryInfo.class);
        reset();
        queryInfo.putAll(query);
    }

    private void addQueryRule(String field, String operation, Object value) {
        if(queryInfo.containsKey(field)) {
            queryInfo.get(field).put(operation, value);
        } else {
            queryInfo.put(field, getRecord(value, operation));
        }
    }

    public QueryInfo getQueryInfo() {
        return queryInfo;
    }

    public void reset() {
        queryInfo.clear();
        sort = null;
        fieldIds = null;
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
