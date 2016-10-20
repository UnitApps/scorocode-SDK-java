package ru.profit_group.scorocode_sdk;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import ru.profit_group.scorocode_sdk.Callbacks.CallbackApplicationStatistic;
import ru.profit_group.scorocode_sdk.Callbacks.CallbackDocumentSaved;
import ru.profit_group.scorocode_sdk.Callbacks.CallbackFindDocument;
import ru.profit_group.scorocode_sdk.Callbacks.CallbackRemoveDocument;
import ru.profit_group.scorocode_sdk.Responses.data.ResponseRemove;
import ru.profit_group.scorocode_sdk.Responses.statistic.ResponseAppStatistic;
import ru.profit_group.scorocode_sdk.scorocode_objects.Document;
import ru.profit_group.scorocode_sdk.scorocode_objects.DocumentInfo;
import ru.profit_group.scorocode_sdk.scorocode_objects.Query;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ScorocodeSdkMethodsTests {

    private static final String APP_ID = "305ffd6cc32832f6819bf4e4f4707848";
    private static final String CLIENT_KEY = "962066371eefc0d1850a76c7ab14c1dc";
    private static final String MASTER_KEY = "383499df2748bb4560745d5da67f5e41";
    private static final String TEST_COLLECTION_NAME = "testscorocodesdkcollection";
    private static final String NUMBER_FIELD_1 = "numberField1";
    private static final String TEXT_FIELD_1 = "textField1";

    private static final String FIELD_FOR_REMOVE_TEST = "field for remove test";

    @Before
    public void setUp() throws Exception {
        ScorocodeSdk.initWith(APP_ID, CLIENT_KEY, MASTER_KEY, null, null, null, null);
        addDocument(TEXT_FIELD_1, FIELD_FOR_REMOVE_TEST);
    }

    @Test
    public void testGetStatistic() {
        try {
            ScorocodeSdk.getApplicationStatistic(
                    new CallbackApplicationStatistic() {
                        @Override
                        public void onRequestSucceed(ResponseAppStatistic appStatistic) {
                            assertEquals(false, appStatistic.isError());
                            assertEquals(null, appStatistic.getErrCode());
                            assertEquals(null, appStatistic.getErrMsg());

                        }

                        @Override
                        public void onRequestFailed(String errorCode, String errorMessage) {
                            assertNotEquals(null, errorCode);
                            assertNotEquals(null, errorMessage);
                        }
                    }
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testDocumentClassUploadNewFile() throws InterruptedException {
        final Document document = new Document(TEST_COLLECTION_NAME);
        document.setField(NUMBER_FIELD_1, 1);
        document.setField(TEXT_FIELD_1, "any text");

        final CountDownLatch signal = new CountDownLatch(1);
        document.saveDocument(new CallbackDocumentSaved() {
            @Override
            public void onDocumentSaved() {
                signal.countDown();
            }

            @Override
            public void onDocumentSaveFailed(String errorCode, String errorMessage) {
                fail("метод не смог выполнить сохранение документа.");
                assertNotEquals(null, errorCode);
                assertNotEquals(null, errorMessage);
                signal.countDown();
            }
        });

        signal.await();// wait for callback
    }

    @Test
    public void testRemoveDocument() throws InterruptedException {
        Query query = new Query(TEST_COLLECTION_NAME);
        query.equalTo(TEXT_FIELD_1, FIELD_FOR_REMOVE_TEST);

        final CountDownLatch signal = new CountDownLatch(1);

        query.findDocuments(new CallbackFindDocument() {
            @Override
            public void onDocumentFound(List<DocumentInfo> documentInfos) {
                assertNotNull("Не было найдено ни одного документа", documentInfos);

                assertNotEquals("Документ для удаления не найден. " +
                        "Ошибка в работе Sdk. Возможно поврежден " +
                        "метод сохранения документа saveDocument(...)", documentInfos.size(), 1);

                final Document document = new Document(TEST_COLLECTION_NAME);
                document.getDocumentById(documentInfos.get(0).getId(), new CallbackFindDocument() {
                    @Override
                    public void onDocumentFound(List<DocumentInfo> documentInfos) {
                        document.removeDocument(new CallbackRemoveDocument() {
                            @Override
                            public void onRemoveSucceed(ResponseRemove responseRemove) {
                                ResponseRemove.Result result = responseRemove.getResult();
                                assertEquals("Документ не был удален", result.getCount(), 0);
                                signal.countDown();
                            }

                            @Override
                            public void onRemoveFailed(String errorCode, String errorMessage) {
                                fail("документ не был удален.");
                                signal.countDown();
                            }
                        });
                    }

                    @Override
                    public void onDocumentNotFound(String errorCode, String errorMessage) {
                        fail("документ не был найден.");
                        signal.countDown();
                    }
                });

            }

            @Override
            public void onDocumentNotFound(String errorCode, String errorMessage) {
                fail("документ не был найден.");
                signal.countDown();
            }
        });

        signal.await();
    }

    private void addDocument(String field, Object value) {
        Document document = new Document(TEST_COLLECTION_NAME);
        document.setField(field, value);

        document.saveDocument(new CallbackDocumentSaved() {
            @Override
            public void onDocumentSaved() {

            }

            @Override
            public void onDocumentSaveFailed(String errorCode, String errorMessage) {
                fail("не удалось инициализировать тесты");
            }
        });
    }


}