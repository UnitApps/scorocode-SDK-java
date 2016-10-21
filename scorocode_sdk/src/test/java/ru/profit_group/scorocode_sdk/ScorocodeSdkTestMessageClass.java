package ru.profit_group.scorocode_sdk;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.HashMap;
import java.util.concurrent.CountDownLatch;

import ru.profit_group.scorocode_sdk.Callbacks.CallbackRegisterUser;
import ru.profit_group.scorocode_sdk.Callbacks.CallbackRemoveDocument;
import ru.profit_group.scorocode_sdk.Callbacks.CallbackSendEmail;
import ru.profit_group.scorocode_sdk.Callbacks.CallbackSendPush;
import ru.profit_group.scorocode_sdk.Callbacks.CallbackSendSms;
import ru.profit_group.scorocode_sdk.Requests.messages.MessageEmail;
import ru.profit_group.scorocode_sdk.Requests.messages.MessagePush;
import ru.profit_group.scorocode_sdk.Requests.messages.MessageSms;
import ru.profit_group.scorocode_sdk.Responses.data.ResponseRemove;
import ru.profit_group.scorocode_sdk.scorocode_objects.Document;
import ru.profit_group.scorocode_sdk.scorocode_objects.Message;
import ru.profit_group.scorocode_sdk.scorocode_objects.Query;
import ru.profit_group.scorocode_sdk.scorocode_objects.User;

import static ru.profit_group.scorocode_sdk.ScorocodeTestHelper.APP_ID;
import static ru.profit_group.scorocode_sdk.ScorocodeTestHelper.CLIENT_KEY;
import static ru.profit_group.scorocode_sdk.ScorocodeTestHelper.MASTER_KEY;
import static ru.profit_group.scorocode_sdk.ScorocodeTestHelper.printError;

/**
 * Created by Peter Staranchuk on 10/21/16
 */

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ScorocodeSdkTestMessageClass {
    @BeforeClass
    public static void setUp() throws Exception {
        ScorocodeSdk.initWith(APP_ID, CLIENT_KEY, MASTER_KEY, null, null, null, null);

        removeTestUsers();
    }

    private static void removeTestUsers() throws InterruptedException {
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        Query query = new Query("users");
        query.equalTo("testUser", true);
        query.removeDocument(new CallbackRemoveDocument() {
            @Override
            public void onRemoveSucceed(ResponseRemove responseRemove) {
                try {
                    createTestUser(1);
                    createTestUser(2);
                    createTestUser(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                countDownLatch.countDown();
            }

            @Override
            public void onRemoveFailed(String errorCode, String errorMessage) {
                try {
                    createTestUser(1);
                    createTestUser(2);
                    createTestUser(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                countDownLatch.countDown();
            }
        });
        countDownLatch.await();
    }

    private static void createTestUser(int number) throws InterruptedException {
        User user = new User();
        Document document = new Document("users");
        document.setField("testUser", true);

        final CountDownLatch latch = new CountDownLatch(1);
        user.register("scorocodeSdkTestUser"+number, "scorocodeSdkTestUser"+number+"@testdomain.com", "qwerty", document.getDocumentContent(), new CallbackRegisterUser() {
            @Override
            public void onRegisterSucceed() {
                latch.countDown();
            }

            @Override
            public void onRegisterFailed(String errorCode, String errorMessage) {
                printError("не удалось создать тестового пользователя. Условия теста нарушены", errorCode, errorMessage);
                latch.countDown();
            }
        });

        latch.await();
    }

    @Test
    public void test1SendEmail() throws InterruptedException {
        final CountDownLatch countDownLatch = new CountDownLatch(1);

        Message message = new Message();
        MessageEmail messageEmail = new MessageEmail("anyFrom", "anySubkect", "anyText");
        message.sendEmail(messageEmail, new CallbackSendEmail() {
            @Override
            public void onEmailSend() {
                countDownLatch.countDown();
            }

            @Override
            public void onEmailSendFailed(String errorCode, String errorMessage) {
                printError("не удалось отправить сообщение", errorCode, errorMessage);
                countDownLatch.countDown();
            }
        });

        countDownLatch.await();
    }

    @Test
    public void test2SendEmailWithQuery() throws InterruptedException {
        final CountDownLatch countDownLatch = new CountDownLatch(1);

        Query query = new Query("users");
        query.equalTo("testUser", true);

        Message message = new Message();
        MessageEmail messageEmail = new MessageEmail("anyFrom", "anySubject", "anyText");

        message.sendEmail(messageEmail, query, new CallbackSendEmail() {
            @Override
            public void onEmailSend() {
                countDownLatch.countDown();
            }

            @Override
            public void onEmailSendFailed(String errorCode, String errorMessage) {
                printError("не удалось отправить email сообщение", errorCode, errorMessage);
                countDownLatch.countDown();
            }
        });

        countDownLatch.await();
    }

    @Test
    public void test3SendPush() throws InterruptedException {
        final CountDownLatch countDownLatch = new CountDownLatch(1);

        Message message = new Message();
        MessagePush messagePush = new MessagePush("text", null);
        message.sendPush(messagePush, new CallbackSendPush() {
            @Override
            public void onPushSended() {
                countDownLatch.countDown();
            }

            @Override
            public void onPushSendFailed(String errorCode, String errorMessage) {
                printError("не удалось отправить push сообщение", errorCode, errorMessage);
                countDownLatch.countDown();
            }
        });

        countDownLatch.await();
    }

    @Test
    public void test4SendPushWithData() throws InterruptedException {
        final CountDownLatch countDownLatch = new CountDownLatch(1);

        Message message = new Message();
        HashMap<String, Object> data = new HashMap<>();
        data.put("field1", "any field");
        data.put("field2", 2);

        MessagePush messagePush = new MessagePush("text", data);
        message.sendPush(messagePush, new CallbackSendPush() {
            @Override
            public void onPushSended() {
                countDownLatch.countDown();
            }

            @Override
            public void onPushSendFailed(String errorCode, String errorMessage) {
                printError("не удалось отправить push сообщение", errorCode, errorMessage);
                countDownLatch.countDown();
            }
        });

        countDownLatch.await();
    }

    @Test
    public void test5SendPushWithDataAndQuery() throws InterruptedException {
        final CountDownLatch countDownLatch = new CountDownLatch(1);

        HashMap<String, Object> data = new HashMap<>();
        data.put("field1", "any field");
        data.put("field2", 2);

        Query query = new Query("users");
        query.equalTo("testUser", true);

        MessagePush messagePush = new MessagePush("text", data);

        Message message = new Message();
        message.sendPush(messagePush, query, new CallbackSendPush() {
            @Override
            public void onPushSended() {
                countDownLatch.countDown();
            }

            @Override
            public void onPushSendFailed(String errorCode, String errorMessage) {
                printError("не удалось отправить push сообщение", errorCode, errorMessage);
                countDownLatch.countDown();
            }
        });

        countDownLatch.await();
    }

    @Test
    public void test6SendSms() throws InterruptedException {
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        MessageSms messageSms = new MessageSms("any text");

        Message message = new Message();
        message.sendSms(messageSms, new CallbackSendSms() {
            @Override
            public void onSmsSended() {
                countDownLatch.countDown();
            }

            @Override
            public void onSmsSendFailed(String errorCode, String errorMessage) {
                printError("не удалось отправить sms сообщение", errorCode, errorMessage);
                countDownLatch.countDown();
            }
        });

        countDownLatch.await();
    }

    @Test
    public void test7SendSms() throws InterruptedException {
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        MessageSms messageSms = new MessageSms("any text");

        Query query = new Query("users");
        query.equalTo("testUser", true);

        Message message = new Message();
        message.sendSms(messageSms, query, new CallbackSendSms() {
            @Override
            public void onSmsSended() {
                countDownLatch.countDown();
            }

            @Override
            public void onSmsSendFailed(String errorCode, String errorMessage) {
                printError("не удалось отправить sms сообщение", errorCode, errorMessage);
                countDownLatch.countDown();
            }
        });

        countDownLatch.await();
    }
}
