package ru.profit_group.scorocode_sdk.scorocode_objects;

import org.bson.BSON;
import org.bson.BSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ru.profit_group.scorocode_sdk.Callbacks.CallbackDeleteFile;
import ru.profit_group.scorocode_sdk.Callbacks.CallbackDocumentSaved;
import ru.profit_group.scorocode_sdk.Callbacks.CallbackFindDocument;
import ru.profit_group.scorocode_sdk.Callbacks.CallbackGetDocumentById;
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
    protected String collectionName;
    protected String documentId;
    protected DocumentInfo documentContent;
    protected Update update;

    public Document(String collectionName) {
        this.collectionName = collectionName;
        documentContent = new DocumentInfo();
        update = new Update();
    }

    public DocumentInfo getDocumentContent() {
        return documentContent;
    }

    public void getDocumentById(final String documentId, final CallbackGetDocumentById callbackGetDocumentById) {
        Query query = new Query(collectionName);
        query.equalTo("_id", documentId);

        ScorocodeSdk.findDocument(collectionName, query, null, null, null, null, new CallbackFindDocument() {
            @Override
            public void onDocumentFound(List<DocumentInfo> documentInfos) {
                Document.this.documentId = documentId;
                if(documentInfos != null && documentInfos.size() > 0) {
                    documentContent = documentInfos.get(0);
                    callbackGetDocumentById.onDocumentFound(documentInfos.get(0));
                }
            }

            @Override
            public void onDocumentNotFound(String errorCode, String errorMessage) {
                Document.this.documentId = null;
                callbackGetDocumentById.onDocumentNotFound(errorCode, errorMessage);
            }
        });
    }

    public void saveDocument(final CallbackDocumentSaved callbackDocumentSaved) {
        if(documentId == null) {
            ScorocodeSdk.insertDocument(collectionName, documentContent, new CallbackInsert() {
                @Override
                public void onInsertSucceed(ResponseInsert responseInsert) {
                    documentContent = responseInsert.getResult();
                    documentId = documentContent.getId();
                    callbackDocumentSaved.onDocumentSaved();
                }

                @Override
                public void onInsertFailed(String errorCode, String errorMessage) {
                    callbackDocumentSaved.onDocumentSaveFailed(errorCode, errorMessage);
                }
            });
        } else {
            HashMap<String, String> query = new HashMap<>();
            query.put("_id", documentId);

            ScorocodeSdk.updateDocumentById(collectionName, query, update.getUpdateInfo(), new CallbackUpdateDocumentById() {
                @Override
                public void onUpdateByIdSucceed(ResponseUpdateById requestUpdateById) {
                    documentContent = requestUpdateById.getResult();
                    documentId = documentContent.getId();
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
        query.equalTo("_id", documentId);

        ScorocodeSdk.removeDocument(collectionName, query, null, callback);
    }

    public Object getField(String field) {
        return documentContent.get(field);
    }

    public void setField(String field, Object value) {
        documentContent.put(field, value);
    }

    public void uploadFile(String fieldName, String fileName, String contenToUploadInBase64, CallbackUploadFile callback) {
        ScorocodeSdk.uploadFile(collectionName,
                documentId, fieldName, fileName, contenToUploadInBase64, callback);
    }

    public String getFileLink(String fieldName, String fileName) {
        return ScorocodeSdk.getFileLink(collectionName, fieldName, documentId, fileName);
    }

    public void removeFile(String fieldName, String fileName, CallbackDeleteFile callback) {
        ScorocodeSdk.deleteFile(collectionName, documentId, fieldName, fileName, callback);
    }

    public Update updateDocument() {
        return update;
    }

    public static List<DocumentInfo> decodeDocumentsList(String base64data) {

        try {
            byte[] bson = ru.profit_group.scorocode_sdk.scorocode_objects.Base64.decode(base64data);
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
