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
import ru.profit_group.scorocode_sdk.scorocode_objects.DocumentInfo;
import ru.profit_group.scorocode_sdk.scorocode_objects.Message;
import ru.profit_group.scorocode_sdk.scorocode_objects.Query;
import ru.profit_group.scorocode_sdk.scorocode_objects.RegexOptions;
import ru.profit_group.scorocode_sdk.scorocode_objects.Script;
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
import ru.profit_group.scorocode_sdk.scorocode_objects.UpdateInfo;
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

    private DocumentInfo _doc;
    private Query _query;
    private UpdateInfo updateInfo;
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

        testApiMethods();

        //logout user tests inside testApiMethods
        //insert document tests inside testApiMethods
    }

    private void setTestDocumentData() {
        _documentId = "AD9sMllwbd";
        _fieldName = "test";
        _fileName = "30T7Dc9zw68.jpg";
    }

    private void setTestDocSetRequest() {
        updateInfo = new UpdateInfo();
        HashMap<String, Object> request = new HashMap<>();
        request.put("exampleField", "Сегодня 2011 июня, и это день рождения Мюриэл! Мюриэл сейчас 105. С днём рождения, Мюриэл!");
        updateInfo.put("$set", request);
    }

    private void setTestQuery() {
        _query = new Query(COLLECTION_NAME);
        _query.equalTo("exampleField", "Сегодня 2010 июня, и это день рождения Мюриэл! Мюриэл сейчас 105. С днём рождения, Мюриэл!");
    }

    private void setTestDoc() {
        _doc = new DocumentInfo();
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

    private void testApiMethods() {
        ScorocodeSdk.loginUser(EMAIL, PASSWORD, new CallbackLoginUser() {
                    @Override
                    public void onLoginSucceed(ResponseLogin responseLogin) {
                        Log.d(TAG, "SUCCESS");

                            ScorocodeSdk.setSessionId(responseLogin.getResult().getSessionId());
//                        testGetStatistic();
                        testRegisterUser();

                            /*testLogoutUser();
                            testInsertDocument();
                            testRemoveDocument();
                            testUpdateDocument();
                            testUpdateDocumentById();
                            testFindDocument();
                            testCountDocument();
                            testUploadFile();
                            testGetFileLink();
                            testDeleteFile(); // TODO - server side error
                            testSendEmail();
                            testSendSms();
                            testSendPush();
                            testSendScript();*/

                            testUserClass(); //FULLY TESTED
                            testDocumentClass(); //FULLY TESTED
//                            testQueryClass();//FULLY TESTED
//                        testMessageClass();//FULLY TESTED
//                        testScriptClass();//FULLY TESTED

                    }


                    @Override
                    public void onLoginFailed(String errorCode, String errorMessage) {
                        Log.d(TAG, "FAILURE");
                    }
                }
        );
    }

    private void testMessageClass() {
        Message message = new Message();
        Query query = new Query("USERS");
        query.equalTo("_id", "XukL1FrVoL");

        MessageEmail messageEmail = new MessageEmail("Peter", "Any subject", "Any text");
        message.sendEmail(messageEmail, null, new CallbackSendEmail() {
            @Override
            public void onEmailSend() {
                Log.d("","");
            }

            @Override
            public void onEmailSendFailed(String errorCode, String errorMessage) {
                Log.d("","");
            }
        });


        MessagePush messagePush = new MessagePush("Any text", null);
        message.sendPush(messagePush, null, new CallbackSendPush() {
            @Override
            public void onPushSended() {
                Log.d("","");
            }

            @Override
            public void onPushSendFailed(String errorCode, String errorMessage) {
                Log.d("","");
            }
        });

        MessageSms messageSms = new MessageSms("Hello world");
        message.sendSms(messageSms, null, new CallbackSendSms() {
            @Override
            public void onSmsSended() {
                Log.d("","");
            }

            @Override
            public void onSmsSendFailed(String errorCode, String errorMessage) {
                Log.d("","");
            }
        });

    }

    private void testQueryClass() {
        testQueryClassFindDocument();
//        testQueryClassRemoveDocument();
//        testQueryClassCountDocument();
//        testQueryClassUpdateDocument();
//        testQueryClassWithRawJsonQuery();
//        testQueryClassTestAllQueryMethods();
    }

    private void testScriptClass() {
        Script script = new Script();
        script.runScript("57e1503b48e5f54441189790", new CallbackSendScript() {
            @Override
            public void onScriptSended() {
                Log.d("", "");
            }

            @Override
            public void onScriptSendFailed(String errorCode, String errorMessage) {
                Log.d("", "");
            }
        });
    }

    private void testQueryClassTestAllQueryMethods() {
        List<Object> numbers = new ArrayList<>();
        numbers.add(1);
        numbers.add(5);
        numbers.add(10);
        numbers.add(15);


        List<Object> containsAllNumbers = new ArrayList<>();
        containsAllNumbers.add(1);
        containsAllNumbers.add(2);
        containsAllNumbers.add(3);
        containsAllNumbers.add(900);

        List<Object> notContainsInList = new ArrayList<>();
        notContainsInList.add(1);
        notContainsInList.add(111);
        notContainsInList.add(11);
        notContainsInList.add(50);

        RegexOptions regexOptions = new RegexOptions();
        regexOptions.setRegexCaseInsenssitive();

        Query query = new Query(COLLECTION_NAME)
//                .equalTo("number3", 10)
//                .notEqualTo("number3", 10)
//                .containedIn("number3", numbers)
//                .containsAll("array1", containsAllNumbers)
//                .notContainedIn("number3", notContainsInList)
//                .greaterThan("number3", 5)
//                .greaterThenOrEqualTo("number3", 10)
//                .lessThan("number3", 50)
//                .lessThanOrEqualTo("number3", 10)
//                .exists("number2")
//                .doesNotExist("number2")
//                .contains("exampleField", "BC", regexOptions)
                .startsWith("exampleField", "ab", regexOptions)
                .startsWith("anotherExampleField", "a", regexOptions)
//                .endsWith("exampleField", "B", regexOptions)
                ;

//        Query queryAdditional = new Query(COLLECTION_NAME).greaterThan("number3", 500);
//        query.and("number3", queryAdditional);
        query.findDocuments(new CallbackFindDocument() {
            @Override
            public void onDocumentFound(List<DocumentInfo> documentInfos) {
                Log.d("","");
            }

            @Override
            public void onDocumentNotFound(String errorCode, String errorMessage) {
                Log.d("","");
            }
        });



        Query query1 = new Query(COLLECTION_NAME).greaterThan("number3", 50);
        Query query2 = new Query(COLLECTION_NAME).equalTo("number3", 1);

        query1.or("number3", query2);

        query1.findDocuments(new CallbackFindDocument() {
            @Override
            public void onDocumentFound(List<DocumentInfo> documentInfos) {
                Log.d("","");
            }

            @Override
            public void onDocumentNotFound(String errorCode, String errorMessage) {
                Log.d("","");
            }
        });

    }

    private void testQueryClassWithRawJsonQuery() {
        Query query = new Query(COLLECTION_NAME);
        query.raw("{\"_id\": {\"$eq\": \"W9vrMS9SuW\"}}");

        query.findDocuments(new CallbackFindDocument() {
            @Override
            public void onDocumentFound(List<DocumentInfo> documentInfos) {
                Log.d("", "");
            }

            @Override
            public void onDocumentNotFound(String errorCode, String errorMessage) {
                Log.d("", "");
            }
        });
    }

    private void testQueryClassUpdateDocument() {
        Query query = new Query(COLLECTION_NAME);
        query.equalTo("number3", 10);

        Update update = new Update()
                .set("number2", 199)
                .set("numberField", 111)
                .addToSet("array1", 900);

        query.updateDocument(update, new CallbackUpdateDocument() {
            @Override
            public void onUpdateSucceed(ResponseUpdate responseUpdate) {
                Log.d("","");
            }

            @Override
            public void onUpdateFailed(String errorCode, String errorMessage) {
                Log.d("","");
            }
        });
    }

    private void testQueryClassCountDocument() {
        Query query = new Query(COLLECTION_NAME);
        query.greaterThan("number2",1);

        query.countDocuments(new CallbackCountDocument() {
            @Override
            public void onDocumentsCounted(ResponseCount responseCount) {
                Log.d("","");
            }

            @Override
            public void onCountFailed(String errorCode, String errorMessage) {
                Log.d("","");
            }
        });
    }

    private void testQueryClassRemoveDocument() {
        Query query = new Query(COLLECTION_NAME);
        query.equalTo("_id", "aJfkipJags");

        query.removeDocument(new CallbackRemoveDocument() {
            @Override
            public void onRemoveSucceed(ResponseRemove responseRemove) {
                Log.d("","");
            }

            @Override
            public void onRemoveFailed(String errorCode, String errorMessage) {
                Log.d("","");
            }
        });
    }

    private void testQueryClassFindDocument() {
        Query query = new Query(COLLECTION_NAME);
        query.equalTo("_id","W9vrMS9SuW");

        List<String> fieldsForSearch = new ArrayList<>();
        fieldsForSearch.add("exampleField");
        fieldsForSearch.add("number3");
        query.setFieldsForSearch(fieldsForSearch);

        query.findDocuments(new CallbackFindDocument() {
            @Override
            public void onDocumentFound(List<DocumentInfo> documentInfos) {
                Log.d("","");
            }

            @Override
            public void onDocumentNotFound(String errorCode, String errorMessage) {
                Log.d("","");
            }
        });
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
            public void onDocumentFound(List<DocumentInfo> documentInfos) {
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
            public void onDocumentFound(List<DocumentInfo> documentInfos) {
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
            public void onDocumentFound(List<DocumentInfo> documentInfos) {
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
        document.getDocumentById("KH3JCojAyT", new CallbackFindDocument() {
            @Override
            public void onDocumentFound(List<DocumentInfo> documentInfos) {
                List<Object> objects = new ArrayList<>();
                objects.add(1);
                objects.add(2);

                document
                        .updateDocument()
                        .push("array1", 1)
//                        .pull("array2", 3)
//                        .pullAll("array3", objects)
//                        .addToSet("array4", 7)
//                        .popFirst("arrayForPopFirts")
//                        .popLast("arrayForPopLast")
//                        .set("exampleField", "random Any1")
//                        .set("anotherExampleField", "random Any2")
//                        .inc("numberField", 2)
//                        .currentDate("date1")
//                        .currentDate("date2")
//                        .mul("numberForMulTest", 3)
//                        .min("number2", 10)
//                        .max("number3", 10)
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
            public void onDocumentFound(List<DocumentInfo> documentInfos) {
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

        user.register("anyperson1111", "anyperson1111@gmail.com", "test1111", doc.getDocumentContent(), new CallbackRegisterUser() {
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
        Query query = new Query(COLLECTION_NAME);
        query.equalTo("_id", "vFPzvFAen2");

        ScorocodeSdk.removeDocument(COLLECTION_NAME, query, 1, new CallbackRemoveDocument() {
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
        Query query = new Query(COLLECTION_NAME);
        query.equalTo("_id", "vWsqeUa4Ov");

        Update update = new Update();
        update.set("exampleField", "testtest");

        ScorocodeSdk.updateDocument(COLLECTION_NAME, query, update.getUpdateInfo(), 1, new CallbackUpdateDocument() {
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

        ScorocodeSdk.updateDocumentById(COLLECTION_NAME, updateQuery, updateInfo, new CallbackUpdateDocumentById() {
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
            public void onDocumentFound(List<DocumentInfo> documentInfos) {
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
        HashMap<String, Object> data = new HashMap<>();
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
        HashMap<String, Object> pool = new HashMap<>();
        pool.put("collname", "mycollection");
        pool.put("key", "exampleField");
        pool.put("val", "Сегодня 18 июня, и это день рождения Мюриэл! Мюриэл сейчас 20. С днём рождения, Мюриэл!");

        final String scriptId = "57e1503b48e5f54441189790";

        ScorocodeSdk.runScript(scriptId, pool, new CallbackSendScript() {
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
                            Log.d("", "SUCCESS");
                        }

                        @Override
                        public void onRequestFailed(String errorCode, String errorMessage) {
                            Log.d("", "FAILURE");
                        }
                    }
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
