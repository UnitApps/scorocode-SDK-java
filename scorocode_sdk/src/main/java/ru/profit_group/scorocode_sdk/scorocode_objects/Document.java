package ru.profit_group.scorocode_sdk.scorocode_objects;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.profit_group.scorocode_sdk.Responses.ResponseCodes;
import ru.profit_group.scorocode_sdk.Responses.ResponseString;
import ru.profit_group.scorocode_sdk.Responses.data.ResponseRemove;
import ru.profit_group.scorocode_sdk.ScorocodeSdk;

/**
 * Created by Peter Staranchuk on 10/6/16
 */

public class Document {
    private String _collectionName;
    private String _documentId;
    private HashMap<String, String> _docToInsert;
    private Update _update;

    public Document(String collectionName) {
        this._collectionName = collectionName;
        _docToInsert = new HashMap<>();
        _update = new Update();
    }

    public void getDocumentById(String collName, final String documentId, final Callback<ResponseString> callback) throws Exception {
        Query query = new Query("id", "$eq", documentId);

        ScorocodeSdk.findDocument(ScorocodeSdk.getAppId(), ScorocodeSdk.getClientKey(), ScorocodeSdk.getMasterKey(), ScorocodeSdk.getSessionId(),
                collName, query, null, null, null, null, new Callback<ResponseString>() {
                    @Override
                    public void onResponse(Call<ResponseString> call, Response<ResponseString> response) {
                        _documentId = documentId;
                        callback.onResponse(call, response);
                    }

                    @Override
                    public void onFailure(Call<ResponseString> call, Throwable t) {
                        _documentId = null;
                        callback.onFailure(call, t);
                    }
                });
    }

    public void saveDocument(Callback callback) {
        if(_documentId == null) {
            ScorocodeSdk.insertDocument(
                    ScorocodeSdk.getAppId(), ScorocodeSdk.getClientKey(), ScorocodeSdk.getMasterKey(),
                    ScorocodeSdk.getSessionId(), _collectionName, _docToInsert, callback);
        } else {
            HashMap<String, String> query = new HashMap<>();
            query.put("_id", _documentId); // TODO relocate all string constants in one file

            HashMap<String, HashMap<String, Object>> doc = new HashMap<>();
            //TODO add logic which construct doc object.

            ScorocodeSdk.updateDocumentById(ScorocodeSdk.getAppId(), ScorocodeSdk.getClientKey(), ScorocodeSdk.getMasterKey(),
                    ScorocodeSdk.getSessionId(), _collectionName, query, doc, callback);
        }
    }

    public void removeDocument(Callback<ResponseRemove> callback) {
        ScorocodeSdk.removeDocument(ScorocodeSdk.getAppId(), ScorocodeSdk.getClientKey(), ScorocodeSdk.getMasterKey(),
                ScorocodeSdk.getSessionId(), _collectionName, null, null, callback);
    }

    public String getField(String field) {
        return _docToInsert.get(field);
    }

    public void setField(String field, String value) {
        _docToInsert.put(field, value);
    }

    public void uploadDocument(String fieldName, String fileName, String contenToUpload, Callback<ResponseCodes> callback) {
        if(ScorocodeSdk.getMasterKey() == null && ScorocodeSdk.getFileKey() == null) {
            return;
        }

        ScorocodeSdk.uploadFile(ScorocodeSdk.getAppId(), ScorocodeSdk.getClientKey(), getFileAccessKey(), ScorocodeSdk.getSessionId(), _collectionName,
                _documentId, fieldName, fileName, contenToUpload, callback);
    }

    private String getFileAccessKey() {
        return ScorocodeSdk.getMasterKey() != null? ScorocodeSdk.getMasterKey() : ScorocodeSdk.getFileKey();
    }

    public String getFileLink(String fieldName, String fileName) {
        return ScorocodeSdk.getFileLink(ScorocodeSdk.getAppId(), _collectionName, fieldName, _documentId, fileName);
    }

    public void deleteFile(String fieldName, String fileName, Callback<ResponseString> callback) {
        ScorocodeSdk.deleteFile(ScorocodeSdk.getAppId(), ScorocodeSdk.getClientKey(), getFileAccessKey(), ScorocodeSdk.getSessionId(),
                _collectionName, _documentId, fieldName, fileName, callback);
    }

    public Update updateDocument() {
        return _update;
    }
}
