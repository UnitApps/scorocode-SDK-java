package ru.profit_group.scorocode_sdk.scorocode_objects;

import org.bson.BSON;
import org.bson.BSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import ru.profit_group.scorocode_sdk.Callbacks.CallbackDeleteFile;
import ru.profit_group.scorocode_sdk.Callbacks.CallbackDocumentSaved;
import ru.profit_group.scorocode_sdk.Callbacks.CallbackFindDocument;
import ru.profit_group.scorocode_sdk.Callbacks.CallbackInsert;
import ru.profit_group.scorocode_sdk.Callbacks.CallbackRemoveDocument;
import ru.profit_group.scorocode_sdk.Callbacks.CallbackUpdateDocumentById;
import ru.profit_group.scorocode_sdk.Callbacks.CallbackUploadFile;
import ru.profit_group.scorocode_sdk.Responses.data.ResponseInsert;
import ru.profit_group.scorocode_sdk.Responses.data.ResponseUpdateById;
import ru.profit_group.scorocode_sdk.ScorocodeSdk;

/**
 * Created by Peter Staranchuk on 10/6/16
 */

public class Document {
    private String collectionName;
    private String _documentId;
    private DocumentInfo _docToInsert;
    private Update _update;

    public Document(String collectionName) {
        this.collectionName = collectionName;
        _docToInsert = new DocumentInfo();
        _update = new Update();
    }

    public DocumentInfo getDocumentContent() {
        return _docToInsert;
    }

    public void getDocumentById(final String documentId, final CallbackFindDocument callbackFindDocument) {
        Query query = new Query(collectionName);
        query.equalTo("_id", documentId);

        ScorocodeSdk.findDocument(collectionName, query, null, null, null, null, new CallbackFindDocument() {
            @Override
            public void onDocumentFound(List<DocumentInfo> documentInfos) {
                _documentId = documentId;
                callbackFindDocument.onDocumentFound(documentInfos);
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
            ScorocodeSdk.insertDocument(collectionName, _docToInsert, new CallbackInsert() {
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

            ScorocodeSdk.updateDocumentById(collectionName, query, _update.getUpdateInfo(), new CallbackUpdateDocumentById() {
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
        Query query = new Query(collectionName);
        query.equalTo("_id", _documentId);

        ScorocodeSdk.removeDocument(collectionName, query, null, callback);
    }

    public Object getField(String field) {
        return _docToInsert.get(field);
    }

    public void setField(String field, String value) {
        _docToInsert.put(field, value);
    }

    public void uploadFile(String fieldName, String fileName, String contenToUploadInBase64, CallbackUploadFile callback) {
        ScorocodeSdk.uploadFile(collectionName,
                _documentId, fieldName, fileName, contenToUploadInBase64, callback);
    }

    public String getFileLink(String fieldName, String fileName) {
        return ScorocodeSdk.getFileLink(collectionName, fieldName, _documentId, fileName);
    }

    public void removeFile(String fieldName, String fileName, CallbackDeleteFile callback) {
        ScorocodeSdk.deleteFile(collectionName, _documentId, fieldName, fileName, callback);
    }

    public Update updateDocument() {
        return _update;
    }

    public static List<DocumentInfo> decodeDocumentsList(String base64data) {

        try {
            byte[] bson = android.util.Base64.decode(base64data, android.util.Base64.DEFAULT);
            BSONObject bsonObject = BSON.decode(bson);

            HashMap<Integer, HashMap<String, String>> documentMap = (HashMap<Integer, HashMap<String, String>>) bsonObject.toMap();

            List<DocumentInfo> documentInfos = new ArrayList<>();
            for(int i = 0; i < documentMap.size(); i++) {
                HashMap<String, String> document = documentMap.get(String.valueOf(i));

                DocumentInfo documentInfo = new DocumentInfo();
                for(String key : document.keySet()) {
                    documentInfo.put(key, document.get(key));
                }

                documentInfos.add(documentInfo);
            }

            return documentInfos;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
