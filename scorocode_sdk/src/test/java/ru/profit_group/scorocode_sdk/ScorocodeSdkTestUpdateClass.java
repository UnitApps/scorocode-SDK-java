package ru.profit_group.scorocode_sdk;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import ru.profit_group.scorocode_sdk.Callbacks.CallbackDocumentSaved;
import ru.profit_group.scorocode_sdk.Callbacks.CallbackRemoveDocument;
import ru.profit_group.scorocode_sdk.Callbacks.CallbackUpdateDocument;
import ru.profit_group.scorocode_sdk.Responses.data.ResponseRemove;
import ru.profit_group.scorocode_sdk.Responses.data.ResponseUpdate;
import ru.profit_group.scorocode_sdk.scorocode_objects.Document;
import ru.profit_group.scorocode_sdk.scorocode_objects.Query;
import ru.profit_group.scorocode_sdk.scorocode_objects.Update;

import static org.junit.Assert.fail;
import static ru.profit_group.scorocode_sdk.ScorocodeTestHelper.APP_ID;
import static ru.profit_group.scorocode_sdk.ScorocodeTestHelper.CLIENT_KEY;
import static ru.profit_group.scorocode_sdk.ScorocodeTestHelper.DATE_FIELD_1;
import static ru.profit_group.scorocode_sdk.ScorocodeTestHelper.MASTER_KEY;
import static ru.profit_group.scorocode_sdk.ScorocodeTestHelper.NUMBER_FIELD_1;
import static ru.profit_group.scorocode_sdk.ScorocodeTestHelper.TEST_COLLECTION_NAME;
import static ru.profit_group.scorocode_sdk.ScorocodeTestHelper.TEXT_FIELD_1;
import static ru.profit_group.scorocode_sdk.ScorocodeTestHelper.printError;

/**
 * Created by Peter Staranchuk on 10/21/16
 */

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ScorocodeSdkTestUpdateClass {

    @BeforeClass
    public static void setUp() throws Exception {
        ScorocodeSdk.initWith(APP_ID, CLIENT_KEY, MASTER_KEY, null, null, null, null);

        createTestDocument();
    }

    @AfterClass
    public static void tearDown() {
        Query query = new Query(TEST_COLLECTION_NAME);
        query.removeDocument(new CallbackRemoveDocument() {
            @Override
            public void onRemoveSucceed(ResponseRemove responseRemove) {

            }

            @Override
            public void onRemoveFailed(String errorCode, String errorMessage) {

            }
        });
    }

    private static void createTestDocument() throws InterruptedException {
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        Document document = new Document(TEST_COLLECTION_NAME);
        document.saveDocument(new CallbackDocumentSaved() {
            @Override
            public void onDocumentSaved() {
                countDownLatch.countDown();
            }

            @Override
            public void onDocumentSaveFailed(String errorCode, String errorMessage) {
                countDownLatch.countDown();
            }
        });

        countDownLatch.await();
    }

    @Test
    public void test1SetFieldTest() throws InterruptedException {

        Update update = new Update();
        update.set(TEXT_FIELD_1, "any test string");

        Query query = new Query(TEST_COLLECTION_NAME);

        final CountDownLatch countDownLatch = new CountDownLatch(1);
        query.updateDocument(update, new CallbackUpdateDocument() {
            @Override
            public void onUpdateSucceed(ResponseUpdate responseUpdate) {
                countDownLatch.countDown();
            }

            @Override
            public void onUpdateFailed(String errorCode, String errorMessage) {
                printError("не удалось обновить документ", errorCode, errorMessage);
                countDownLatch.countDown();
            }
        });

        countDownLatch.await();
    }


    @Test
    public void test2IncrementFieldTest() throws InterruptedException {

        Update update = new Update();
        update.inc(NUMBER_FIELD_1, 10);

        Query query = new Query(TEST_COLLECTION_NAME);

        final CountDownLatch countDownLatch = new CountDownLatch(1);
        query.updateDocument(update, new CallbackUpdateDocument() {
            @Override
            public void onUpdateSucceed(ResponseUpdate responseUpdate) {
                countDownLatch.countDown();
            }

            @Override
            public void onUpdateFailed(String errorCode, String errorMessage) {
                printError("не удалось обновить документ", errorCode, errorMessage);
                countDownLatch.countDown();
            }
        });

        countDownLatch.await();
    }

    @Test
    public void test3IncrementFieldByNegativeValueTest() throws InterruptedException {

        Update update = new Update();
        update.inc(NUMBER_FIELD_1, -10);

        Query query = new Query(TEST_COLLECTION_NAME);

        final CountDownLatch countDownLatch = new CountDownLatch(1);
        query.updateDocument(update, new CallbackUpdateDocument() {
            @Override
            public void onUpdateSucceed(ResponseUpdate responseUpdate) {
                countDownLatch.countDown();
            }

            @Override
            public void onUpdateFailed(String errorCode, String errorMessage) {
                printError("не удалось обновить документ", errorCode, errorMessage);
                countDownLatch.countDown();
            }
        });

        countDownLatch.await();
    }

    @Test
    public void test4AddToSet() throws InterruptedException {
        Update update = new Update();
        update.addToSet("arrayField1", 101);

        Query query = new Query(TEST_COLLECTION_NAME);

        final CountDownLatch countDownLatch = new CountDownLatch(1);
        query.updateDocument(update, new CallbackUpdateDocument() {
            @Override
            public void onUpdateSucceed(ResponseUpdate responseUpdate) {
                countDownLatch.countDown();
            }

            @Override
            public void onUpdateFailed(String errorCode, String errorMessage) {
                printError("не удалось обновить документ", errorCode, errorMessage);
                countDownLatch.countDown();
            }
        });

        countDownLatch.await();
    }

    @Test
    public void test5SetCurrentDate() throws InterruptedException {
        Update update = new Update();
        update.setCurrentDate(DATE_FIELD_1);

        Query query = new Query(TEST_COLLECTION_NAME);

        final CountDownLatch countDownLatch = new CountDownLatch(1);
        query.updateDocument(update, new CallbackUpdateDocument() {
            @Override
            public void onUpdateSucceed(ResponseUpdate responseUpdate) {
                countDownLatch.countDown();
            }

            @Override
            public void onUpdateFailed(String errorCode, String errorMessage) {
                printError("не удалось обновить документ", errorCode, errorMessage);
                countDownLatch.countDown();
            }
        });

        countDownLatch.await();
    }

    @Test
    public void test6SetMaxFromFieldAndValue() throws InterruptedException {
        Update update = new Update();
        update.max(NUMBER_FIELD_1, 10);

        Query query = new Query(TEST_COLLECTION_NAME);

        final CountDownLatch countDownLatch = new CountDownLatch(1);
        query.updateDocument(update, new CallbackUpdateDocument() {
            @Override
            public void onUpdateSucceed(ResponseUpdate responseUpdate) {
                countDownLatch.countDown();
            }

            @Override
            public void onUpdateFailed(String errorCode, String errorMessage) {
                printError("не удалось обновить документ", errorCode, errorMessage);
                countDownLatch.countDown();
            }
        });

        countDownLatch.await();
    }

    @Test
    public void test7SetMinFromFieldAndValue() throws InterruptedException {
        Update update = new Update();
        update.min(NUMBER_FIELD_1, -10);

        Query query = new Query(TEST_COLLECTION_NAME);

        final CountDownLatch countDownLatch = new CountDownLatch(1);
        query.updateDocument(update, new CallbackUpdateDocument() {
            @Override
            public void onUpdateSucceed(ResponseUpdate responseUpdate) {
                countDownLatch.countDown();
            }

            @Override
            public void onUpdateFailed(String errorCode, String errorMessage) {
                printError("не удалось обновить документ", errorCode, errorMessage);
                countDownLatch.countDown();
            }
        });

        countDownLatch.await();
    }

    @Test
    public void test8MultiplyFieldByValueTest() throws InterruptedException {
        Update update = new Update();
        update.mul(NUMBER_FIELD_1, 3);

        Query query = new Query(TEST_COLLECTION_NAME);

        final CountDownLatch countDownLatch = new CountDownLatch(1);
        query.updateDocument(update, new CallbackUpdateDocument() {
            @Override
            public void onUpdateSucceed(ResponseUpdate responseUpdate) {
                countDownLatch.countDown();
            }

            @Override
            public void onUpdateFailed(String errorCode, String errorMessage) {
                printError("не удалось обновить документ", errorCode, errorMessage);
                countDownLatch.countDown();
            }
        });

        countDownLatch.await();
    }

    @Test
    public void test9PushValueTest() throws InterruptedException {
        Update update = new Update();

        Query query = new Query(TEST_COLLECTION_NAME);

        final CountDownLatch countDownLatch = new CountDownLatch(1);

        CallbackUpdateDocument callbackUpdateDocument = new CallbackUpdateDocument() {
            @Override
            public void onUpdateSucceed(ResponseUpdate responseUpdate) {
                countDownLatch.countDown();
            }

            @Override
            public void onUpdateFailed(String errorCode, String errorMessage) {
                printError("не удалось обновить документ", errorCode, errorMessage);
                countDownLatch.countDown();
            }
        };

        update.push(ScorocodeTestHelper.ARRAY_FIELD_1, 3);
        query.updateDocument(new Update().push(ScorocodeTestHelper.ARRAY_FIELD_1, 3), callbackUpdateDocument);

        countDownLatch.await();
    }

    @Test
    public void test10PushNegativeValueTest() throws InterruptedException {
        Query query = new Query(TEST_COLLECTION_NAME);

        final CountDownLatch countDownLatch = new CountDownLatch(1);

        CallbackUpdateDocument callbackUpdateDocument = new CallbackUpdateDocument() {
            @Override
            public void onUpdateSucceed(ResponseUpdate responseUpdate) {
                countDownLatch.countDown();
            }

            @Override
            public void onUpdateFailed(String errorCode, String errorMessage) {
                printError("не удалось обновить документ", errorCode, errorMessage);
                countDownLatch.countDown();
            }
        };

        query.updateDocument(new Update().push(ScorocodeTestHelper.ARRAY_FIELD_1, -3), callbackUpdateDocument);

        countDownLatch.await();
    }


    @Test
    public void test11PopFirstFromArrayTest() throws InterruptedException {
        Update update = new Update();
        update.popFirst(ScorocodeTestHelper.ARRAY_FIELD_1);

        Query query = new Query(TEST_COLLECTION_NAME);

        final CountDownLatch countDownLatch = new CountDownLatch(1);
        query.updateDocument(update, new CallbackUpdateDocument() {
            @Override
            public void onUpdateSucceed(ResponseUpdate responseUpdate) {
                countDownLatch.countDown();
            }

            @Override
            public void onUpdateFailed(String errorCode, String errorMessage) {
                printError("не удалось обновить документ", errorCode, errorMessage);
                countDownLatch.countDown();
            }
        });

        countDownLatch.await();
    }

    @Test
    public void test12PopLastFromArrayTest() throws InterruptedException {
        Update update = new Update();
        update.popLast(ScorocodeTestHelper.ARRAY_FIELD_1);

        Query query = new Query(TEST_COLLECTION_NAME);

        final CountDownLatch countDownLatch = new CountDownLatch(1);
        query.updateDocument(update, new CallbackUpdateDocument() {
            @Override
            public void onUpdateSucceed(ResponseUpdate responseUpdate) {
                countDownLatch.countDown();
            }

            @Override
            public void onUpdateFailed(String errorCode, String errorMessage) {
                printError("не удалось обновить документ", errorCode, errorMessage);
                countDownLatch.countDown();
            }
        });

        countDownLatch.await();
    }

    @Test
    public void test13PullValueTest() throws InterruptedException {
        Update update = new Update();
        update.pull(ScorocodeTestHelper.ARRAY_FIELD_1, -3);

        Query query = new Query(TEST_COLLECTION_NAME);

        final CountDownLatch countDownLatch = new CountDownLatch(1);
        query.updateDocument(update, new CallbackUpdateDocument() {
            @Override
            public void onUpdateSucceed(ResponseUpdate responseUpdate) {
                countDownLatch.countDown();
            }

            @Override
            public void onUpdateFailed(String errorCode, String errorMessage) {
                printError("не удалось обновить документ", errorCode, errorMessage);
                countDownLatch.countDown();
            }
        });

        countDownLatch.await();
    }

    @Test
    public void test13PullAllValuesTest() throws InterruptedException {
        Update update = new Update();
        List<Object> numbers = new ArrayList<>();
        numbers.add(1);
        numbers.add(2);
        numbers.add(3);

        update.pullAll(ScorocodeTestHelper.ARRAY_FIELD_1, numbers);

        Query query = new Query(TEST_COLLECTION_NAME);

        final CountDownLatch countDownLatch = new CountDownLatch(1);
        query.updateDocument(update, new CallbackUpdateDocument() {
            @Override
            public void onUpdateSucceed(ResponseUpdate responseUpdate) {
                countDownLatch.countDown();
            }

            @Override
            public void onUpdateFailed(String errorCode, String errorMessage) {
                printError("не удалось обновить документ", errorCode, errorMessage);
                countDownLatch.countDown();
            }
        });

        countDownLatch.await();
    }

}
