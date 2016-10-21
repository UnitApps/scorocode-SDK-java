package ru.profit_group.scorocode_sdk;

import org.apache.commons.codec.binary.Base64;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.List;
import java.util.concurrent.CountDownLatch;

import ru.profit_group.scorocode_sdk.Callbacks.CallbackDeleteFile;
import ru.profit_group.scorocode_sdk.Callbacks.CallbackDocumentSaved;
import ru.profit_group.scorocode_sdk.Callbacks.CallbackFindDocument;
import ru.profit_group.scorocode_sdk.Callbacks.CallbackRemoveDocument;
import ru.profit_group.scorocode_sdk.Callbacks.CallbackUploadFile;
import ru.profit_group.scorocode_sdk.Responses.data.ResponseRemove;
import ru.profit_group.scorocode_sdk.scorocode_objects.Document;
import ru.profit_group.scorocode_sdk.scorocode_objects.DocumentInfo;
import ru.profit_group.scorocode_sdk.scorocode_objects.Query;

import static org.junit.Assert.*;
import static ru.profit_group.scorocode_sdk.ScorocodeTestHelper.APP_ID;
import static ru.profit_group.scorocode_sdk.ScorocodeTestHelper.CLIENT_KEY;
import static ru.profit_group.scorocode_sdk.ScorocodeTestHelper.MASTER_KEY;

/**
 * Created by Peter Staranchuk on 10/20/16
 */


@FixMethodOrder(MethodSorters.DEFAULT)
public class ScorocodeSdkTestDocumentClass {

    private static final String NUMBER_FIELD_1 = "numberField1";
    private static final String TEXT_FIELD_1 = "textField1";

    private static final String FIELD_FOR_REMOVE_TEST = "field for remove test";
    private static final String FIELD_FOR_SEARCH_BY_ID = "field for search by id";
    public static final String NEW_TEST_VALUE = "NEW TEST VALUE";
    private static String FILE_FIELD_NAME;
    private static String fileName;

    @BeforeClass
    public static void setUp() throws Exception {
        ScorocodeSdk.initWith(APP_ID, CLIENT_KEY, MASTER_KEY, null, null, null, null);

        FILE_FIELD_NAME = "fileField1";
        fileName = "file.txt";

        addDocument(TEXT_FIELD_1, FIELD_FOR_REMOVE_TEST);
        addDocument(TEXT_FIELD_1, FIELD_FOR_SEARCH_BY_ID);
        addDocument(TEXT_FIELD_1, FIELD_FOR_SEARCH_BY_ID);
        addDocument(TEXT_FIELD_1, FIELD_FOR_SEARCH_BY_ID);
        addDocument(TEXT_FIELD_1, FIELD_FOR_SEARCH_BY_ID);
        addDocument(TEXT_FIELD_1, FIELD_FOR_SEARCH_BY_ID);

    }

    @AfterClass
    public static void tearDown() {
        Query query = new Query(ScorocodeTestHelper.TEST_COLLECTION_NAME);
        query.removeDocument(new CallbackRemoveDocument() {
            @Override
            public void onRemoveSucceed(ResponseRemove responseRemove) {

            }

            @Override
            public void onRemoveFailed(String errorCode, String errorMessage) {

            }
        });
    }

    @Test
    public void testDocumentClassUploadNewFile() throws InterruptedException {
        final Document document = new Document(ScorocodeTestHelper.TEST_COLLECTION_NAME);
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
    public void testUpdateDocument() throws InterruptedException {
        Query query = new Query(ScorocodeTestHelper.TEST_COLLECTION_NAME);
        query.equalTo(TEXT_FIELD_1, FIELD_FOR_SEARCH_BY_ID);

        final CountDownLatch signal = new CountDownLatch(1);

        query.findDocuments(new CallbackFindDocument() {
            @Override
            public void onDocumentFound(List<DocumentInfo> documentInfos) {
                final Document document = new Document(ScorocodeTestHelper.TEST_COLLECTION_NAME);
                document.getDocumentById(documentInfos.get(0).getId(), new CallbackFindDocument() {
                    @Override
                    public void onDocumentFound(List<DocumentInfo> documentInfos) {
                        document.updateDocument()
                                .set(TEXT_FIELD_1, NEW_TEST_VALUE);

                        document.saveDocument(new CallbackDocumentSaved() {
                            @Override
                            public void onDocumentSaved() {
                                signal.countDown();
                            }

                            @Override
                            public void onDocumentSaveFailed(String errorCode, String errorMessage) {
                                fail("ошибка при сохранении документа");
                                signal.countDown();
                            }
                        });
                    }

                    @Override
                    public void onDocumentNotFound(String errorCode, String errorMessage) {
                        fail("документ не был найден");
                        signal.countDown();
                    }
                });
            }

            @Override
            public void onDocumentNotFound(String errorCode, String errorMessage) {
                fail("документ не был найден");
                signal.countDown();
            }
        });

        signal.await();
    }

    @Test
    public void testRemoveDocument() throws InterruptedException {
        Query query = new Query(ScorocodeTestHelper.TEST_COLLECTION_NAME);
        query.equalTo(TEXT_FIELD_1, FIELD_FOR_REMOVE_TEST);
        query.setLimit(1);

        final CountDownLatch signal = new CountDownLatch(1);

        query.findDocuments(new CallbackFindDocument() {
            @Override
            public void onDocumentFound(List<DocumentInfo> documentInfos) {
                assertNotNull("Не было найдено ни одного документа", documentInfos);

                assertEquals("Документ для удаления не найден. " +
                        "Ошибка в работе Sdk. Возможно поврежден " +
                        "метод сохранения документа saveDocument(...)", documentInfos.size(), 1);

                final Document document = new Document(ScorocodeTestHelper.TEST_COLLECTION_NAME);
                document.getDocumentById(documentInfos.get(0).getId(), new CallbackFindDocument() {
                    @Override
                    public void onDocumentFound(List<DocumentInfo> documentInfos) {
                        document.removeDocument(new CallbackRemoveDocument() {
                            @Override
                            public void onRemoveSucceed(ResponseRemove responseRemove) {
                                ResponseRemove.Result result = responseRemove.getResult();
                                assertNotEquals("Документ не был удален", result.getCount(), 0);
                                assertEquals("Лимит не был установлен. Проверьте метод setLimit(...) класса Query", result.getCount(), 1);
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

    @Test
    public void testFieldSetAndGetMethods() {
        Document document = new Document(ScorocodeTestHelper.TEST_COLLECTION_NAME);

        String testValue1 = "VALUE1";
        document.setField(TEXT_FIELD_1, testValue1);
        String value1 = (String) document.getField(TEXT_FIELD_1);
        assertEquals("Не удалось установить поле документа. Возможно метод setField поврежден", testValue1, value1);

        Integer testValue2 = 1;
        document.setField(TEXT_FIELD_1, testValue2);
        Integer value2 = (Integer) document.getField(TEXT_FIELD_1);
        assertEquals("Не удалось установить поле документа. Возможно метод setField поврежден", testValue2, value2);
    }

    @Test
    public void testUploadFile() throws InterruptedException {
        final byte[] binaryData = "Hello world".getBytes();
        final String contentInBase64 = Base64.encodeBase64String(binaryData);

        Query query = new Query(ScorocodeTestHelper.TEST_COLLECTION_NAME);
        query.equalTo(TEXT_FIELD_1, FIELD_FOR_SEARCH_BY_ID);

        final CountDownLatch signal = new CountDownLatch(1);
        query.findDocuments(new CallbackFindDocument() {
            @Override
            public void onDocumentFound(List<DocumentInfo> documentInfos) {
                assertNotNull("не удалось получить ни одного документа", documentInfos);
                assertNotEquals("не удалось найти документ", 0, documentInfos.size());
                final Document document = new Document(ScorocodeTestHelper.TEST_COLLECTION_NAME);
                document.getDocumentById(documentInfos.get(0).getId(), new CallbackFindDocument() {
                    @Override
                    public void onDocumentFound(List<DocumentInfo> documentInfos) {
                        document.uploadFile(FILE_FIELD_NAME, fileName, contentInBase64, new CallbackUploadFile() {
                            @Override
                            public void onDocumentUploaded() {
                                signal.countDown();
                            }

                            @Override
                            public void onDocumentUploadFailed(String errorCode, String errorMessage) {
                                fail("не удалось загрузить файл" + errorCode + " " + errorMessage);
                                signal.countDown();
                            }
                        });
                    }

                    @Override
                    public void onDocumentNotFound(String errorCode, String errorMessage) {
                        fail("не удалось найти документ для загрузки файла" + errorCode + " " + errorMessage);
                        signal.countDown();
                    }
                });

            }

            @Override
            public void onDocumentNotFound(String errorCode, String errorMessage) {
                fail("не удалось найти документ для размещения файла" + errorCode + " " + errorMessage);
                signal.countDown();
            }
        });

        signal.await();
    }

    @Test
    public void testGetFileLink() throws InterruptedException {
        Query query = new Query(ScorocodeTestHelper.TEST_COLLECTION_NAME);
        query.equalTo(TEXT_FIELD_1, FIELD_FOR_SEARCH_BY_ID);

        final CountDownLatch countDownLatch = new CountDownLatch(1);

        query.findDocuments(new CallbackFindDocument() {
            @Override
            public void onDocumentFound(List<DocumentInfo> documentInfos) {
                testDocumentList(documentInfos);

                final Document document = new Document(ScorocodeTestHelper.TEST_COLLECTION_NAME);
                document.getDocumentById(documentInfos.get(0).getId(), new CallbackFindDocument() {
                    @Override
                    public void onDocumentFound(List<DocumentInfo> documentInfos) {
                        String fileLink = document.getFileLink(FILE_FIELD_NAME, fileName);
                        assertNotNull("не удалось получить ссылку на файл", fileLink);
                        countDownLatch.countDown();
                    }

                    @Override
                    public void onDocumentNotFound(String errorCode, String errorMessage) {
                        fail("не удалось найти документ " + errorCode + " " + errorMessage);
                        countDownLatch.countDown();
                    }
                });

            }

            @Override
            public void onDocumentNotFound(String errorCode, String errorMessage) {
                fail("не удалось найти документ " + errorCode + " " + errorMessage);
                countDownLatch.countDown();
            }
        });

        countDownLatch.await();
    }

    @Test @Ignore
    public void testRemoveFile() throws InterruptedException {
        Query query = new Query(ScorocodeTestHelper.TEST_COLLECTION_NAME);
        query.equalTo(FILE_FIELD_NAME, fileName);

        final CountDownLatch countDownLatch = new CountDownLatch(1);

        query.findDocuments(new CallbackFindDocument() {
            @Override
            public void onDocumentFound(List<DocumentInfo> documentInfos) {
                testDocumentList(documentInfos);
                final Document document = new Document(ScorocodeTestHelper.TEST_COLLECTION_NAME);
                document.getDocumentById(documentInfos.get(0).getId(), new CallbackFindDocument() {
                    @Override
                    public void onDocumentFound(List<DocumentInfo> documentInfos) {
                        document.removeFile(FILE_FIELD_NAME, fileName, new CallbackDeleteFile() {
                            @Override
                            public void onDocumentDeleted() {
                                countDownLatch.countDown();
                            }

                            @Override
                            public void onDetelionFailed(String errorCodes, String errorMessage) {
                                ScorocodeTestHelper.printError("не удалось удалить файл", errorCodes, errorMessage);
                                countDownLatch.countDown();
                            }
                        });
                    }

                    @Override
                    public void onDocumentNotFound(String errorCode, String errorMessage) {
                        ScorocodeTestHelper.printError("не удалось найти документ", errorCode, errorMessage);
                        countDownLatch.countDown();
                    }
                });

            }

            @Override
            public void onDocumentNotFound(String errorCode, String errorMessage) {
                ScorocodeTestHelper.printError("не удалось найти документ", errorCode, errorMessage);
                countDownLatch.countDown();
            }
        });

        countDownLatch.await();
    }

    private void testDocumentList(List<DocumentInfo> documentInfos) {
        assertNotNull("Не удалось получить ни одного документа с сервера", documentInfos);
        assertNotEquals("Не удалось найти документ для чтобы получить сслку на файл", 1);
    }

    private static void addDocument(String field, Object value) {
        Document document = new Document(ScorocodeTestHelper.TEST_COLLECTION_NAME);
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