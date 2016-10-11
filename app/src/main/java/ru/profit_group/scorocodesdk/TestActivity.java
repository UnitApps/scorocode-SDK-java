package ru.profit_group.scorocodesdk;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.profit_group.scorocode_sdk.Requests.messages.MessageEmail;
import ru.profit_group.scorocode_sdk.Requests.messages.MessagePush;
import ru.profit_group.scorocode_sdk.Requests.messages.MessageSms;
import ru.profit_group.scorocode_sdk.scorocode_objects.Query;
import ru.profit_group.scorocode_sdk.scorocode_objects.Sort;
import ru.profit_group.scorocode_sdk.Responses.ResponseString;
import ru.profit_group.scorocode_sdk.Responses.data.ResponseCount;
import ru.profit_group.scorocode_sdk.Responses.data.ResponseInsert;
import ru.profit_group.scorocode_sdk.Responses.data.ResponseRemove;
import ru.profit_group.scorocode_sdk.Responses.data.ResponseUpdate;
import ru.profit_group.scorocode_sdk.Responses.data.ResponseUpdateById;
import ru.profit_group.scorocode_sdk.Responses.statistic.ResponseAppStatistic;
import ru.profit_group.scorocode_sdk.Responses.ResponseCodes;
import ru.profit_group.scorocode_sdk.Responses.user.ResponseLogin;
import ru.profit_group.scorocode_sdk.ScorocodeSdk;

public class TestActivity extends AppCompatActivity {

    private static final String TAG = "SCOROCODE_SDK_TEST";

    private static final String APP_ID = "305ffd6cc32832f6819bf4e4f4707848";
    private static final String CLIENT_KEY = "962066371eefc0d1850a76c7ab14c1dc";
    private static final String ACCESS_KEY = "383499df2748bb4560745d5da67f5e41";

    private static final String EMAIL = "petr.staranchukTest11@gmail.com";
    private static final String PASSWORD = "qwertyTest1";
    private static final String COLLECTION_NAME = "mycollection";
    private static final String SCRIPT_ID = "1234565";
    public static final String MASTER_KEY = "383499df2748bb4560745d5da67f5e41";

    private String _sessionId = null;
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
        HashMap<String, String> request = new HashMap<>();
        request.put("$eq", "Сегодня 18 июня, и это день рождения Мюриэл! Мюриэл сейчас 20. С днём рождения, Мюриэл!");
        _query.put("exampleField", request);
    }

    private void setTestDoc() {
        _doc = new HashMap<>();
        _doc.put("exampleField", "Сегодня 18 июня, и это день рождения Мюриэл! Мюриэл сейчас 20. С днём рождения, Мюриэл!");
        _doc.put("anotherExampleField", "Не знаю, что и сказать. Когда-то я хотел быть астрофизиком. К сожалению, это правда.");
    }

    private void testRegisterUser() {
        ScorocodeSdk.registerUser(APP_ID, CLIENT_KEY, "PeterStaranchukTest11", EMAIL, PASSWORD, _doc,
                new Callback<ResponseCodes>() {
                    @Override
                    public void onResponse(Call<ResponseCodes> call, Response<ResponseCodes> response) {
                        Log.d(TAG, "REGISTER USER: SUCCESS");
                    }

                    @Override
                    public void onFailure(Call<ResponseCodes> call, Throwable t) {
                        Log.d(TAG, "REGISTER USER: FAILURE");
                    }
                });
    }

    private void testLoginUser() {
        ScorocodeSdk.loginUser(APP_ID, CLIENT_KEY, EMAIL, PASSWORD, new Callback<ResponseLogin>() {
            @Override
            public void onResponse(Call<ResponseLogin> call, Response<ResponseLogin> response) {
                Log.d(TAG, "REGISTER USER: SUCCESS");

                if(response != null && response.body() != null && response.body().getResult() != null) {
                    _sessionId = response.body().getResult().getSessionId();
//                    testLogoutUser();
//                    testInsertDocument();
//                    testRemoveDocument();
//                    testUpdateDocument();
//                    testUpdateDocumentById();
                    testFindDocument();
//                    testCountDocument();
//                    testUploadDocument();
//                    testGetFileLink();
//                    testDeleteFile(); // TODO fix - serverside error
//                    testSendEmail();
//                    testSendSms();
//                    testSendPush();
//                    testSendScript();
                }
            }

            @Override
            public void onFailure(Call<ResponseLogin> call, Throwable t) {
                Log.d(TAG, "REGISTER USER: FAILURE");
            }
        });
    }

    private void testLogoutUser() {
        ScorocodeSdk.logoutUser(APP_ID, CLIENT_KEY, _sessionId, new Callback<ResponseCodes>() {
            @Override
            public void onResponse(Call<ResponseCodes> call, Response<ResponseCodes> response) {
                Log.d(TAG, "REGISTER USER: SUCCESS");
            }

            @Override
            public void onFailure(Call<ResponseCodes> call, Throwable t) {
                Log.d(TAG, "REGISTER USER: FAILURE");
            }
        });
    }

    private void testInsertDocument() {
        ScorocodeSdk.insertDocument(APP_ID, CLIENT_KEY, ACCESS_KEY, _sessionId, COLLECTION_NAME, _doc, new Callback<ResponseInsert>() {
            @Override
            public void onResponse(Call<ResponseInsert> call, Response<ResponseInsert> response) {
                Log.d(TAG, "REGISTER USER: SUCCESS");
            }

            @Override
            public void onFailure(Call<ResponseInsert> call, Throwable t) {
                Log.d(TAG, "REGISTER USER: FAILURE");
            }
        });
    }

    private void testRemoveDocument() {
        Query local_query = Query.getSimpleQuery("exampleField", "$eq", "Сегодня 2010 июня, и это день рождения Мюриэл! Мюриэл сейчас 105. С днём рождения, Мюриэл!");

        ScorocodeSdk.removeDocument(APP_ID, CLIENT_KEY, ACCESS_KEY, _sessionId, COLLECTION_NAME, local_query, 1, new Callback<ResponseRemove>() {
            @Override
            public void onResponse(Call<ResponseRemove> call, Response<ResponseRemove> response) {
                Log.d(TAG, "REGISTER USER: SUCCESS");
            }

            @Override
            public void onFailure(Call<ResponseRemove> call, Throwable t) {
                Log.d(TAG, "REGISTER USER: FAILURE");
            }
        });
    }

    private void testUpdateDocument() {
        ScorocodeSdk.updateDocument(APP_ID, CLIENT_KEY, ACCESS_KEY, _sessionId, COLLECTION_NAME, _query, _doc_set, 1, new Callback<ResponseUpdate>() {
            @Override
            public void onResponse(Call<ResponseUpdate> call, Response<ResponseUpdate> response) {
                Log.d(TAG, "SUCCESS");
            }

            @Override
            public void onFailure(Call<ResponseUpdate> call, Throwable t) {
                Log.d(TAG, "FAILURE");
            }
        });
    }

    private void testUpdateDocumentById() {
        HashMap<String, String> updateQuery = new HashMap<>();
        updateQuery.put("_id", "lThEkcUoDZ");

        ScorocodeSdk.updateDocumentById(APP_ID, CLIENT_KEY, ACCESS_KEY, _sessionId, COLLECTION_NAME, updateQuery, _doc_set, new Callback<ResponseUpdateById>() {
            @Override
            public void onResponse(Call<ResponseUpdateById> call, Response<ResponseUpdateById> response) {
                Log.d(TAG, "SUCCESS");
            }

            @Override
            public void onFailure(Call<ResponseUpdateById> call, Throwable t) {
                Log.d(TAG, "FAILURE");
            }
        });
    }

    private void testFindDocument() {
        Sort sort = new Sort();
        sort.put("updatedAt", 1);

        List<String> fieldsList = new ArrayList<>();
        fieldsList.add("exampleField");

        ScorocodeSdk.findDocument(APP_ID, CLIENT_KEY, ACCESS_KEY, _sessionId, COLLECTION_NAME, _query, sort, fieldsList, 1, null, new Callback<ResponseString>() {
            @Override
            public void onResponse(Call<ResponseString> call, Response<ResponseString> response) {
                Log.d(TAG, "SUCCESS");
            }

            @Override
            public void onFailure(Call<ResponseString> call, Throwable t) {
                Log.d(TAG, "FAILURE");
            }
        });
    }

    private void testCountDocument() {
        ScorocodeSdk.getDocumentsCount(APP_ID, CLIENT_KEY, ACCESS_KEY, _sessionId, COLLECTION_NAME, null, new Callback<ResponseCount>() {
            @Override
            public void onResponse(Call<ResponseCount> call, Response<ResponseCount> response) {
                Log.d(TAG, "SUCCESS");
            }

            @Override
            public void onFailure(Call<ResponseCount> call, Throwable t) {
                Log.d(TAG, "FAILURE");
            }
        });
    }

    private void testUploadDocument() {
        String documentId = "nV0p50CDKq";
        String fieldName = "test";
        String fileName = "file.txt";
        String content = "VEhJUyBJUyBGSUxFLUUtRS1FLUUtRS1FIQ==";

        ScorocodeSdk.uploadFile(APP_ID, CLIENT_KEY, ACCESS_KEY, _sessionId, COLLECTION_NAME, documentId, fieldName, fileName, content, new Callback<ResponseCodes>() {
            @Override
            public void onResponse(Call<ResponseCodes> call, Response<ResponseCodes> response) {
                Log.d(TAG, "SUCCESS");
            }

            @Override
            public void onFailure(Call<ResponseCodes> call, Throwable t) {
                Log.d(TAG, "FAILURE");
            }
        });
    }

    private void testGetFileLink() {
       String fileLink = ScorocodeSdk.getFileLink(APP_ID, CLIENT_KEY, _fieldName, _documentId, _fileName);
       Log.d(TAG, "SUCCESS");
    }

    private void testDeleteFile() {
        ScorocodeSdk.deleteFile(APP_ID, CLIENT_KEY, MASTER_KEY, _sessionId, COLLECTION_NAME, _documentId, _fieldName, _fileName, new Callback<ResponseString>() {
            @Override
            public void onResponse(Call<ResponseString> call, Response<ResponseString> response) {
                Log.d(TAG, "SUCCESS");
            }

            @Override
            public void onFailure(Call<ResponseString> call, Throwable t) {
                Log.d(TAG, "FAILURE");
            }
        });
    }

    private void testSendEmail() {
        MessageEmail messageEmail = new MessageEmail("Peter", "Hello", "Hello world");
        String collection = "users"; //users or roles
        ScorocodeSdk.sendEmail(APP_ID, CLIENT_KEY, ACCESS_KEY, _sessionId, collection, null, messageEmail, new Callback<ResponseCodes>() {
            @Override
            public void onResponse(Call<ResponseCodes> call, Response<ResponseCodes> response) {
                Log.d(TAG, "SUCCESS");
            }

            @Override
            public void onFailure(Call<ResponseCodes> call, Throwable t) {
                Log.d(TAG, "FAILURE");
            }
        });
    }

    private void testSendSms() {
        MessageSms messageSms = new MessageSms("Hello world");
        String collection = "users"; //users or roles

        ScorocodeSdk.sendSms(APP_ID, CLIENT_KEY, ACCESS_KEY, _sessionId, collection, null, messageSms, new Callback<ResponseCodes>() {
            @Override
            public void onResponse(Call<ResponseCodes> call, Response<ResponseCodes> response) {
                Log.d(TAG, "SUCCESS");
            }

            @Override
            public void onFailure(Call<ResponseCodes> call, Throwable t) {
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
        ScorocodeSdk.sendPush(APP_ID, CLIENT_KEY, ACCESS_KEY, _sessionId, collection, null, messagePush, new Callback<ResponseCodes>() {
            @Override
            public void onResponse(Call<ResponseCodes> call, Response<ResponseCodes> response) {
                Log.d(TAG, "SUCCESS");
            }

            @Override
            public void onFailure(Call<ResponseCodes> call, Throwable t) {
                Log.d(TAG, "FAILURE");
            }
        });
    }

    private void testSendScript() {
        HashMap<String, String> pool = new HashMap<>();
        pool.put("collname", "mycollection");
        pool.put("key", "exampleField");
        pool.put("val", "Сегодня 18 июня, и это день рождения Мюриэл! Мюриэл сейчас 20. С днём рождения, Мюриэл!");

        ScorocodeSdk.sendScriptTask(APP_ID, CLIENT_KEY, ACCESS_KEY, _sessionId, SCRIPT_ID, pool, new Callback<ResponseCodes>() {
            @Override
            public void onResponse(Call<ResponseCodes> call, Response<ResponseCodes> response) {
                Log.d(TAG, "SUCCESS");
            }

            @Override
            public void onFailure(Call<ResponseCodes> call, Throwable t) {
                Log.d(TAG, "FAILURE");
            }
        });
    }

    private void testGetStatistic() {
        try {
            ScorocodeSdk.getApplicationStatistic(APP_ID, CLIENT_KEY, ACCESS_KEY,
                    new Callback<ResponseAppStatistic>() {
                        @Override
                        public void onResponse(Call<ResponseAppStatistic> call, Response<ResponseAppStatistic> response) {
                            Log.d(TAG, "REGISTER USER: SUCCESS");
                        }

                        @Override
                        public void onFailure(Call<ResponseAppStatistic> call, Throwable t) {
                            Log.d(TAG, "REGISTER USER: FAILURE");
                        }
                    }
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
