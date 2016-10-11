package ru.profit_group.scorocode_sdk.scorocode_objects;

import org.bson.BSON;
import org.bson.BSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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

    public void getDocumentById(String collName, final String documentId, final CallbackFindDocument callbackFindDocument) throws Exception {
        Query query = new Query("id", "$eq", documentId);

        ScorocodeSdk.findDocument(collName, query, null, null, null, null, new CallbackFindDocument() {
            @Override
            public void documentFound(List<String> documentIds) {
                _documentId = documentId;
                callbackFindDocument.documentFound(documentIds);
            }

            @Override
            public void documentNotFound() {
                _documentId = null;
                callbackFindDocument.documentNotFound();
            }
        });
    }

    public void saveDocument(Callback callback) {
        if(_documentId == null) {
            ScorocodeSdk.insertDocument(_collectionName, _docToInsert, callback);
        } else {
            HashMap<String, String> query = new HashMap<>();
            query.put("_id", _documentId); // TODO relocate all string constants in one file

            HashMap<String, HashMap<String, Object>> doc = new HashMap<>();
            //TODO add logic which construct doc object.

            ScorocodeSdk.updateDocumentById(_collectionName, query, doc, callback);
        }
    }

    public void removeDocument(Callback<ResponseRemove> callback) {
        ScorocodeSdk.removeDocument(_collectionName, null, null, callback);
    }

    public String getField(String field) {
        return _docToInsert.get(field);
    }

    public void setField(String field, String value) {
        _docToInsert.put(field, value);
    }

    public void uploadDocument(String fieldName, String fileName, String contenToUpload, Callback<ResponseCodes> callback) {
        ScorocodeSdk.uploadFile(_collectionName,
                _documentId, fieldName, fileName, contenToUpload, callback);
    }

    public String getFileLink(String fieldName, String fileName) {
        return ScorocodeSdk.getFileLink(_collectionName, fieldName, _documentId, fileName);
    }

    public void deleteFile(String fieldName, String fileName, Callback<ResponseString> callback) {
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

    public interface CallbackFindDocument {
        void documentFound(List<String> documentIds);
        void documentNotFound();
    }
}
