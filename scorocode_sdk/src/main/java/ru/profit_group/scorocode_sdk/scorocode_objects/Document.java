package ru.profit_group.scorocode_sdk.scorocode_objects;

import org.bson.BSON;
import org.bson.BSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.profit_group.scorocode_sdk.Callbacks.CallbackDeleteFile;
import ru.profit_group.scorocode_sdk.Callbacks.CallbackDocumentSaved;
import ru.profit_group.scorocode_sdk.Callbacks.CallbackFindDocument;
import ru.profit_group.scorocode_sdk.Callbacks.CallbackInsert;
import ru.profit_group.scorocode_sdk.Callbacks.CallbackRemoveDocument;
import ru.profit_group.scorocode_sdk.Callbacks.CallbackUpdateDocumentById;
import ru.profit_group.scorocode_sdk.Callbacks.CallbackUploadFile;
import ru.profit_group.scorocode_sdk.Responses.ResponseCodes;
import ru.profit_group.scorocode_sdk.Responses.ResponseString;
import ru.profit_group.scorocode_sdk.Responses.data.ResponseInsert;
import ru.profit_group.scorocode_sdk.Responses.data.ResponseRemove;
import ru.profit_group.scorocode_sdk.Responses.data.ResponseUpdateById;
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

    public void getDocumentById(String collName, final String documentId, final CallbackFindDocument callbackFindDocument) throws Exception {
        Query query = new Query("id", "$eq", documentId);

        ScorocodeSdk.findDocument(collName, query, null, null, null, null, new CallbackFindDocument() {
            @Override
            public void onDocumentFound(List<String> documentsIds) {
                _documentId = documentId;
                callbackFindDocument.onDocumentFound(documentsIds);
            }

            @Override
            public void onDocumentNotFound(String errorCode, String errorMessage) {
                _documentId = null;
                callbackFindDocument.onDocumentNotFound(errorCode, errorMessage);
            }
        });
    }

    public void saveDocument(final CallbackDocumentSaved callbackDocumentSaved) {
        if(_documentId == null) {
            ScorocodeSdk.insertDocument(_collectionName, _docToInsert, new CallbackInsert() {
                @Override
                public void onInsertSucceed(ResponseInsert responseInsert) {
                    callbackDocumentSaved.onDocumentSaved();
                }

                @Override
                public void onInsertFailed(String errorCode, String errorMessage) {
                    callbackDocumentSaved.onDocumentSaveFailed(errorCode, errorMessage);
                }
            });
        } else {
            HashMap<String, String> query = new HashMap<>();
            query.put("_id", _documentId);

            HashMap<String, HashMap<String, Object>> doc = new HashMap<>();
            //TODO add logic which construct doc object.

            ScorocodeSdk.updateDocumentById(_collectionName, query, doc, new CallbackUpdateDocumentById() {
                @Override
                public void onUpdateByIdSucceed(ResponseUpdateById requestUpdateById) {
                    callbackDocumentSaved.onDocumentSaved();
                }

                @Override
                public void onUpdateByIdFailed(String errorCode, String errorMessage) {
                    callbackDocumentSaved.onDocumentSaveFailed(errorCode, errorMessage);
                }
            });
        }
    }

    public void removeDocument(CallbackRemoveDocument callback) {
        ScorocodeSdk.removeDocument(_collectionName, null, null, callback);
    }

    public String getField(String field) {
        return _docToInsert.get(field);
    }

    public void setField(String field, String value) {
        _docToInsert.put(field, value);
    }

    public void uploadDocument(String fieldName, String fileName, String contenToUpload, CallbackUploadFile callback) {
        ScorocodeSdk.uploadFile(_collectionName,
                _documentId, fieldName, fileName, contenToUpload, callback);
    }

    public String getFileLink(String fieldName, String fileName) {
        return ScorocodeSdk.getFileLink(_collectionName, fieldName, _documentId, fileName);
    }

    public void deleteFile(String fieldName, String fileName, CallbackDeleteFile callback) {
        ScorocodeSdk.deleteFile(_collectionName, _documentId, fieldName, fileName, callback);
    }

    public Update updateDocument() {
        return _update;
    }

    public static List<String> decodeDocumentsList(String base64data) {

        try {
            byte[] bson = android.util.Base64.decode(base64data, android.util.Base64.DEFAULT);
            BSONObject bsonObject = BSON.decode(bson);

            HashMap<Integer, HashMap<String, String>> documentMap = (HashMap<Integer, HashMap<String, String>>) bsonObject.toMap();

            List<String> documentsIds = new ArrayList<>();
            for(int i = 0; i < documentMap.size(); i++) {
                HashMap<String, String> document = documentMap.get(String.valueOf(i));
                documentsIds.add(document.get("_id"));
            }

            return documentsIds;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
