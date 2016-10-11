package ru.profit_group.scorocode_sdk.scorocode_objects;

import java.util.HashMap;

import retrofit2.Callback;
import ru.profit_group.scorocode_sdk.Responses.ResponseString;
import ru.profit_group.scorocode_sdk.Responses.data.ResponseCount;
import ru.profit_group.scorocode_sdk.Responses.data.ResponseRemove;
import ru.profit_group.scorocode_sdk.Responses.data.ResponseUpdate;
import ru.profit_group.scorocode_sdk.ScorocodeSdk;

/**
 * Created by Peter Staranchuk on 25/09/16
 */
public class Query extends HashMap<String, HashMap<String,String>> {

    private String _collectionName;
    private Integer _limit;
    private Integer _skip;

    public Query(String collectionName) {
        _collectionName = collectionName;
    }

    public static Query getSimpleQuery(String field, String operator, String value) {
        HashMap<String,String> record = new HashMap<>();
        record.put(operator, value);

        Query query = new Query(null);
        query.put(field, record);
        return query;
    }


    public Query(String field, String operator, String value) {
        HashMap<String,String> query = new HashMap<>();
        query.put(operator, value);

        this.put(field, query);
    }

    public void findDocuments(Document.CallbackFindDocument callback) {
        ScorocodeSdk.findDocument(_collectionName, this, null, null, _limit, _skip, callback);
    }

    public void countDocuments(Callback<ResponseCount> callback) {
        ScorocodeSdk.getDocumentsCount(_collectionName, this, callback);
    }

    public void updateDocument(Update update, Callback<ResponseUpdate> callback) {
        HashMap<String, HashMap<String,Object>> doc = update.getUpdateInfo();
        ScorocodeSdk.updateDocument(_collectionName, this, doc, _limit, callback);
    }

    public void removeDocument(Callback<ResponseRemove> callback) {
        ScorocodeSdk.removeDocument(_collectionName, this, _limit, callback);
    }

    public void setLimit(Integer limit) {
        _limit = limit;
    }

    public void setSkip(Integer skip) {
        _skip = skip;
    }
}
