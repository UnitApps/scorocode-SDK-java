package ru.profit_group.scorocodesdk;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ru.profit_group.scorocode_sdk.Callbacks.CallbackApplicationStatistic;
import ru.profit_group.scorocode_sdk.Callbacks.CallbackCountDocument;
import ru.profit_group.scorocode_sdk.Callbacks.CallbackDeleteFile;
import ru.profit_group.scorocode_sdk.Callbacks.CallbackDocumentSaved;
import ru.profit_group.scorocode_sdk.Callbacks.CallbackFindDocument;
import ru.profit_group.scorocode_sdk.Callbacks.CallbackInsert;
import ru.profit_group.scorocode_sdk.Callbacks.CallbackLoginUser;
import ru.profit_group.scorocode_sdk.Callbacks.CallbackLogoutUser;
import ru.profit_group.scorocode_sdk.Callbacks.CallbackRegisterUser;
import ru.profit_group.scorocode_sdk.Callbacks.CallbackRemoveDocument;
import ru.profit_group.scorocode_sdk.Callbacks.CallbackSendEmail;
import ru.profit_group.scorocode_sdk.Callbacks.CallbackSendPush;
import ru.profit_group.scorocode_sdk.Callbacks.CallbackSendScript;
import ru.profit_group.scorocode_sdk.Callbacks.CallbackSendSms;
import ru.profit_group.scorocode_sdk.Callbacks.CallbackUpdateDocument;
import ru.profit_group.scorocode_sdk.Callbacks.CallbackUpdateDocumentById;
import ru.profit_group.scorocode_sdk.Callbacks.CallbackUploadFile;
import ru.profit_group.scorocode_sdk.Requests.messages.MessageEmail;
import ru.profit_group.scorocode_sdk.Requests.messages.MessagePush;
import ru.profit_group.scorocode_sdk.Requests.messages.MessageSms;
import ru.profit_group.scorocode_sdk.scorocode_objects.Document;
import ru.profit_group.scorocode_sdk.scorocode_objects.Query;
import ru.profit_group.scorocode_sdk.scorocode_objects.Sort;
import ru.profit_group.scorocode_sdk.Responses.data.ResponseCount;
import ru.profit_group.scorocode_sdk.Responses.data.ResponseInsert;
import ru.profit_group.scorocode_sdk.Responses.data.ResponseRemove;
import ru.profit_group.scorocode_sdk.Responses.data.ResponseUpdate;
import ru.profit_group.scorocode_sdk.Responses.data.ResponseUpdateById;
import ru.profit_group.scorocode_sdk.Responses.statistic.ResponseAppStatistic;
import ru.profit_group.scorocode_sdk.Responses.user.ResponseLogin;
import ru.profit_group.scorocode_sdk.ScorocodeSdk;
import ru.profit_group.scorocode_sdk.scorocode_objects.Update;
import ru.profit_group.scorocode_sdk.scorocode_objects.User;

public class TestActivity extends AppCompatActivity {

    private static final String TAG = "SCOROCODE_SDK_TEST";

    private static final String APP_ID = "305ffd6cc32832f6819bf4e4f4707848";
    private static final String CLIENT_KEY = "962066371eefc0d1850a76c7ab14c1dc";
    private static final String ACCESS_KEY = "383499df2748bb4560745d5da67f5e41";

    private static final String EMAIL = "petr.staranchukTest11@gmail.com";
    private static final String PASSWORD = "qwertyTest1";
    private static final String COLLECTION_NAME = "mycollection";
    public static final String MASTER_KEY = "383499df2748bb4560745d5da67f5e41";

    private HashMap<String, String> _doc;
    private Query _query;
    private HashMap<String, HashMap<String, Object>> _doc_set;
    private String _documentId;
    private String _fieldName;
    private String _fileName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ScorocodeSdk.initWith(APP_ID, CLIENT_KEY, MASTER_KEY, null, null, null);

        setTestDoc();
        setTestQuery();
        setTestDocSetRequest();
        setTestDocumentData();

        testGetStatistic();
//        testRegisterUser();
        testLoginUser();

        //logout user tests inside testLoginUser
        //insert document tests inside testLoginUser
    }

    private void setTestDocumentData() {
        _documentId = "AD9sMllwbd";
        _fieldName = "test";
        _fileName = "30T7Dc9zw68.jpg";
    }

    private void setTestDocSetRequest() {
        _doc_set = new HashMap<>();
        HashMap<String, Object> request = new HashMap<>();
        request.put("exampleField", "Сегодня 2011 июня, и это день рождения Мюриэл! Мюриэл сейчас 105. С днём рождения, Мюриэл!");
        _doc_set.put("$set", request);
    }

    private void setTestQuery() {
        _query = new Query(COLLECTION_NAME);
        HashMap<String, Object> request = new HashMap<>();
        request.put("$eq", "Сегодня 2010 июня, и это день рождения Мюриэл! Мюриэл сейчас 105. С днём рождения, Мюриэл!");
        _query.put("exampleField", request);
    }

    private void setTestDoc() {
        _doc = new HashMap<>();
        _doc.put("exampleField", "Сегодня 18 июня, и это день рождения Мюриэл! Мюриэл сейчас 20. С днём рождения, Мюриэл!");
        _doc.put("anotherExampleField", "Не знаю, что и сказать. Когда-то я хотел быть астрофизиком. К сожалению, это правда.");
    }

    private void testRegisterUser() {
        ScorocodeSdk.registerUser("PeterStaranchukTest11", EMAIL, PASSWORD, _doc,
                new CallbackRegisterUser() {
                    @Override
                    public void onRegisterSucceed() {
                        Log.d(TAG, "SUCCESS");
                    }

                    @Override
                    public void onRegisterFailed(String errorCode, String errorMessage) {
                        Log.d(TAG, "FAILURE");
                    }
                });
    }

    private void testLoginUser() {
        ScorocodeSdk.loginUser(EMAIL, PASSWORD, new CallbackLoginUser() {
                    @Override
                    public void onLoginSucceed(ResponseLogin responseLogin) {
                        Log.d(TAG, "SUCCESS");

                            ScorocodeSdk.setSessionId(responseLogin.getResult().getSessionId());
                            /*testLogoutUser();
                            testInsertDocument();
                            testRemoveDocument();
                            testUpdateDocument();
                            testUpdateDocumentById();
                            testFindDocument();
                            testCountDocument();
                            testUploadFile();
                            testGetFileLink();
                            testDeleteFile(); // TODO - serverside error
                            testSendEmail();
                            testSendSms();
                            testSendPush();
                            testSendScript();*/

//                            testUserClass(); //FULLY TESTED
                            testDocumentClass();
                        }


                    @Override
                    public void onLoginFailed(String errorCode, String errorMessage) {
                        Log.d(TAG, "FAILURE");
                    }
                }
        );
    }

    private void testDocumentClass() {
        testDocumentClassCreateAndSaveNewDocument();
        testDocumentClassUpdateMethodWhichExist();
        testDocumentClassGetFileLink();
        testDocumentClassUploadNewFile();
        testDocumentClassDeleteFile();
        testDocumentClassRemoveDocument();
    }

    private void testDocumentClassRemoveDocument() {
        final Document document = new Document(COLLECTION_NAME);
        document.getDocumentById("7BOlVr1Acp", new CallbackFindDocument() {
            @Override
            public void onDocumentFound(List<String> documentsIds) {
                Log.d(TAG, "");
                document.removeDocument(new CallbackRemoveDocument() {
                    @Override
                    public void onRemoveSucceed(ResponseRemove responseRemove) {
                        Log.d(TAG, "");
                    }

                    @Override
                    public void onRemoveFailed(String errorCode, String errorMessage) {
                        Log.d(TAG, "");
                    }
                });
            }

            @Override
            public void onDocumentNotFound(String errorCode, String errorMessage) {
                Log.d(TAG, "");
            }
        });
    }

    private void testDocumentClassDeleteFile() {
        final Document document = new Document(COLLECTION_NAME);
        document.getDocumentById("nV0p50CDKq", new CallbackFindDocument() {
            @Override
            public void onDocumentFound(List<String> documentsIds) {
                document.removeFile("test", "any", new CallbackDeleteFile() {
                    @Override
                    public void onDocumentDeleted() {
                        Log.d(TAG, "");
                    }

                    @Override
                    public void onDetelionFailed(String errorCodes, String errorMessage) {
                        Log.d(TAG, "");
                    }
                });
            }

            @Override
            public void onDocumentNotFound(String errorCode, String errorMessage) {
                Log.d(TAG, "");
            }
        });
    }

    private void testDocumentClassUploadNewFile() {
        final Document document = new Document(COLLECTION_NAME);
        document.getDocumentById("nV0p50CDKq", new CallbackFindDocument() {
            @Override
            public void onDocumentFound(List<String> documentsIds) {
                document.uploadFile("test", "any.txt", Base64.encodeToString("hello world".getBytes(), Base64.DEFAULT), new CallbackUploadFile() {
                    @Override
                    public void onDocumentUploaded() {
                        Log.d(TAG, "");
                    }

                    @Override
                    public void onDocumentUploadFailed(String errorCode, String errorMessage) {
                        Log.d(TAG, "");
                    }
                });
            }

            @Override
            public void onDocumentNotFound(String errorCode, String errorMessage) {
                Log.d(TAG, "");
            }
        });
    }

    private void testDocumentClassUpdateMethodWhichExist() {
        final Document document = new Document(COLLECTION_NAME);
        document.getDocumentById("lThEkcUoDZ", new CallbackFindDocument() {
            @Override
            public void onDocumentFound(List<String> documentsIds) {
                List<Object> objectses = new ArrayList<>();
                objectses.add("a");
                objectses.add("b");


                //TODO write multiple operations
                document
                        .updateDocument()
                        .push("array", 1)
                        .pull("array", 3)
                        .pullAll("array", objectses)
                        .addToSet("array", 3)
                        .pop("arrayForPopFirts", Update.ItemToRemovePosition.FIRST)
                        .set("exampleField", "random Any")
                        .inc("numberField", 2)
                        .currentDate("date2", Update.DateTypeSpetification.DATE)
                        .mul("numberForMulTest", 3)
                        .min("number2", 10)
                        .max("number3", 10)
                ;


                document.saveDocument(new CallbackDocumentSaved() {
                    @Override
                    public void onDocumentSaved() {
                        Log.d(TAG, "");
                    }

                    @Override
                    public void onDocumentSaveFailed(String errorCode, String errorMessage) {
                        Log.d(TAG, "");
                    }
                });
                Log.d(TAG, "");
            }

            @Override
            public void onDocumentNotFound(String errorCode, String errorMessage) {
                Log.d(TAG, "");
            }
        });
    }

    private void testDocumentClassGetFileLink() {
        final Document documentWithFile = new Document(COLLECTION_NAME);
        documentWithFile.getDocumentById("nV0p50CDKq", new CallbackFindDocument() {
            @Override
            public void onDocumentFound(List<String> documentsIds) {
                String fileLink = documentWithFile.getFileLink("test", "file.txt");
            }

            @Override
            public void onDocumentNotFound(String errorCode, String errorMessage) {
                Log.d(TAG, "");
            }
        });
    }

    private void testDocumentClassCreateAndSaveNewDocument() {
        Document newDocument = new Document(COLLECTION_NAME);
        newDocument.setField("exampleField", "any1");
        newDocument.setField("anotherExampleField", "any2");
        newDocument.saveDocument(new CallbackDocumentSaved() {
            @Override
            public void onDocumentSaved() {
                Log.d(TAG, "");
            }

            @Override
            public void onDocumentSaveFailed(String errorCode, String errorMessage) {
                Log.d(TAG, "");
            }
        });
    }

    private void testUserClass() {
        User user = new User();
        user.login(EMAIL, PASSWORD, new CallbackLoginUser() {
            @Override
            public void onLoginSucceed(ResponseLogin responseLogin) {
                Log.d(TAG,"");
            }

            @Override
            public void onLoginFailed(String errorCode, String errorMessage) {
                Log.d(TAG,"");
            }
        });

        user.logout(new CallbackLogoutUser() {
            @Override
            public void onLogoutSucceed() {
                Log.d(TAG,"");
            }

            @Override
            public void onLogoutFailed(String errorCode, String errorMessage) {
                Log.d(TAG,"");
            }
        });

        user.register("anyperson11", "anyperson11@gmail.com", "test11", new CallbackRegisterUser() {
            @Override
            public void onRegisterSucceed() {
                Log.d(TAG,"");
            }

            @Override
            public void onRegisterFailed(String errorCode, String errorMessage) {
                Log.d(TAG,"");
            }
        });


        Document doc = new Document("mycollection");
        doc.setField("testField", "test1");
        doc.setField("anotherTestField", "test2");

        user.register("anyperson111", "anyperson111@gmail.com", "test111", doc.getDocumentContent(), new CallbackRegisterUser() {
            @Override
            public void onRegisterSucceed() {
                Log.d(TAG,"");
            }

            @Override
            public void onRegisterFailed(String errorCode, String errorMessage) {
                Log.d(TAG,"");
            }
        });

    }

    private void testLogoutUser() {
        ScorocodeSdk.logoutUser(new CallbackLogoutUser() {
            @Override
            public void onLogoutSucceed() {
                Log.d(TAG, "SUCCESS");
            }

            @Override
            public void onLogoutFailed(String errorCode, String errorMessage) {
                Log.d(TAG, "FAILURE");
            }
        });
    }

    private void testInsertDocument() {
        ScorocodeSdk.insertDocument(COLLECTION_NAME, _doc, new CallbackInsert() {
            @Override
            public void onInsertSucceed(ResponseInsert responseInsert) {
                Log.d(TAG, "SUCCESS");
            }

            @Override
            public void onInsertFailed(String errorCode, String errorMessage) {
                Log.d(TAG, "FAILURE");
            }
        });
    }

    private void testRemoveDocument() {
        Query local_query = Query.getSimpleQuery("exampleField", "$eq", "Сегодня 2010 июня, и это день рождения Мюриэл! Мюриэл сейчас 105. С днём рождения, Мюриэл!");

        ScorocodeSdk.removeDocument(COLLECTION_NAME, local_query, 1, new CallbackRemoveDocument() {
            @Override
            public void onRemoveSucceed(ResponseRemove responseRemove) {
                Log.d(TAG, "SUCCESS");
            }

            @Override
            public void onRemoveFailed(String errorCode, String errorMessage) {
                Log.d(TAG, "FAILURE");
            }
        });
    }

    private void testUpdateDocument() {
        ScorocodeSdk.updateDocument(COLLECTION_NAME, _query, _doc_set, 1, new CallbackUpdateDocument() {
            @Override
            public void onUpdateSucceed(ResponseUpdate responseUpdate) {
                Log.d(TAG, "SUCCESS");
            }

            @Override
            public void onUpdateFailed(String errorCode, String errorMessage) {
                Log.d(TAG, "FAILURE");
            }
        });
    }

    private void testUpdateDocumentById() {
        HashMap<String, String> updateQuery = new HashMap<>();
        updateQuery.put("_id", "lThEkcUoDZ");

        ScorocodeSdk.updateDocumentById(COLLECTION_NAME, updateQuery, _doc_set, new CallbackUpdateDocumentById() {
            @Override
            public void onUpdateByIdSucceed(ResponseUpdateById requestUpdateById) {
                Log.d(TAG, "SUCCESS");
            }

            @Override
            public void onUpdateByIdFailed(String errorCode, String errorMessage) {
                Log.d(TAG, "FAILURE");
            }
        });
    }

    private void testFindDocument() {
        Sort sort = new Sort();
        sort.put("updatedAt", 1);

        List<String> fieldsList = new ArrayList<>();
        fieldsList.add("exampleField");

        ScorocodeSdk.findDocument(COLLECTION_NAME, _query, sort, fieldsList, 10, null, new CallbackFindDocument() {
            @Override
            public void onDocumentFound(List<String> documentsIds) {
                Log.d(TAG, "SUCCESS");
            }

            @Override
            public void onDocumentNotFound(String errorCode, String errorMessage) {
                Log.d(TAG, "FAILURE");
            }
        });
    }

    private void testCountDocument() {
        ScorocodeSdk.getDocumentsCount(COLLECTION_NAME, null, new CallbackCountDocument() {
            @Override
            public void onDocumentsCounted(ResponseCount responseCount) {
                Log.d(TAG, "SUCCESS");
            }

            @Override
            public void onCountFailed(String errorCode, String errorMessage) {
                Log.d(TAG, "FAILURE");
            }
        });
    }

    private void testUploadFile() {
        String documentId = "nV0p50CDKq";
        String fieldName = "test";
        String fileName = "file.txt";
        String content = "VEhJUyBJUyBGSUxFLUUtRS1FLUUtRS1FIQ==";

        ScorocodeSdk.uploadFile(COLLECTION_NAME, documentId, fieldName, fileName, content, new CallbackUploadFile() {
            @Override
            public void onDocumentUploaded() {
                Log.d(TAG, "SUCCESS");
            }

            @Override
            public void onDocumentUploadFailed(String errorCode, String errorMessage) {
                Log.d(TAG, "FAILURE");
            }
        });
    }

    private void testGetFileLink() {
       String fileLink = ScorocodeSdk.getFileLink(COLLECTION_NAME, _fieldName, _documentId, _fileName);
       Log.d(TAG, "SUCCESS");
    }

    private void testDeleteFile() {
        ScorocodeSdk.deleteFile(COLLECTION_NAME, _documentId, _fieldName, _fileName, new CallbackDeleteFile() {
            @Override
            public void onDocumentDeleted() {
                Log.d(TAG, "SUCCESS");
            }

            @Override
            public void onDetelionFailed(String errorCodes, String errorMessage) {
                Log.d(TAG, "FAILURE");
            }
        });
    }

    private void testSendEmail() {
        MessageEmail messageEmail = new MessageEmail("Peter", "Hello", "Hello world");
        String collection = "users"; //users or roles
        ScorocodeSdk.sendEmail(collection, null, messageEmail, new CallbackSendEmail() {
            @Override
            public void onEmailSend() {
                Log.d(TAG, "SUCCESS");
            }

            @Override
            public void onEmailSendFailed(String errorCode, String errorMessage) {
                Log.d(TAG, "FAILURE");
            }
        });
    }

    private void testSendSms() {
        MessageSms messageSms = new MessageSms("Hello world");
        String collection = "users"; //users or roles

        ScorocodeSdk.sendSms(collection, null, messageSms, new CallbackSendSms() {
            @Override
            public void onSmsSended() {
                Log.d(TAG, "SUCCESS");
            }

            @Override
            public void onSmsSendFailed(String errorCode, String errorMessage) {
                Log.d(TAG, "FAILURE");
            }
        });
    }

    private void testSendPush() {
        HashMap<String, String> data = new HashMap<>();
        data.put("key1", "data1");
        data.put("key2", "data2");
        String collection = "users"; //users or roles

        MessagePush messagePush = new MessagePush("Hello World", data);
        ScorocodeSdk.sendPush(collection, null, messagePush, new CallbackSendPush() {
            @Override
            public void onPushSended() {
                Log.d(TAG, "SUCCESS");
            }

            @Override
            public void onPushSendFailed(String errorCode, String errorMessage) {
                Log.d(TAG, "FAILURE");
            }
        });
    }

    private void testSendScript() {
        HashMap<String, String> pool = new HashMap<>();
        pool.put("collname", "mycollection");
        pool.put("key", "exampleField");
        pool.put("val", "Сегодня 18 июня, и это день рождения Мюриэл! Мюриэл сейчас 20. С днём рождения, Мюриэл!");

        final String scriptId = "57484fb91c5666544db25675";

        ScorocodeSdk.sendScriptTask(scriptId, pool, new CallbackSendScript() {
            @Override
            public void onScriptSended() {
                Log.d(TAG, "SUCCESS");
            }

            @Override
            public void onScriptSendFailed(String errorCode, String errorMessage) {
                Log.d(TAG, "FAILURE");
            }
        });
    }

    private void testGetStatistic() {
        try {
            ScorocodeSdk.getApplicationStatistic(
                    new CallbackApplicationStatistic() {
                        @Override
                        public void onRequestSucceed(ResponseAppStatistic appStatistic) {
                            Log.d(TAG, "SUCCESS");
                        }

                        @Override
                        public void onRequestFailed(String errorCode, String errorMessage) {
                            Log.d(TAG, "FAILURE");
                        }
                    }
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
