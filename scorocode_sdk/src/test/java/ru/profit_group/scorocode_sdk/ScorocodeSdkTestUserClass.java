package ru.profit_group.scorocode_sdk;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.List;
import java.util.concurrent.CountDownLatch;

import ru.profit_group.scorocode_sdk.Callbacks.CallbackFindDocument;
import ru.profit_group.scorocode_sdk.Callbacks.CallbackGetDocumentById;
import ru.profit_group.scorocode_sdk.Callbacks.CallbackLoginUser;
import ru.profit_group.scorocode_sdk.Callbacks.CallbackLogoutUser;
import ru.profit_group.scorocode_sdk.Callbacks.CallbackRegisterUser;
import ru.profit_group.scorocode_sdk.Callbacks.CallbackRemoveDocument;
import ru.profit_group.scorocode_sdk.Callbacks.CallbackUpdateDocument;
import ru.profit_group.scorocode_sdk.Responses.data.ResponseRemove;
import ru.profit_group.scorocode_sdk.Responses.data.ResponseUpdate;
import ru.profit_group.scorocode_sdk.Responses.user.ResponseLogin;
import ru.profit_group.scorocode_sdk.scorocode_objects.Document;
import ru.profit_group.scorocode_sdk.scorocode_objects.DocumentInfo;
import ru.profit_group.scorocode_sdk.scorocode_objects.Query;
import ru.profit_group.scorocode_sdk.scorocode_objects.Update;
import ru.profit_group.scorocode_sdk.scorocode_objects.User;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;
import static ru.profit_group.scorocode_sdk.ScorocodeTestHelper.APP_ID;
import static ru.profit_group.scorocode_sdk.ScorocodeTestHelper.CLIENT_KEY;
import static ru.profit_group.scorocode_sdk.ScorocodeTestHelper.MASTER_KEY;
import static ru.profit_group.scorocode_sdk.ScorocodeTestHelper.TEST_COLLECTION_NAME;
import static ru.profit_group.scorocode_sdk.ScorocodeTestHelper.printError;

/**
 * Created by Peter Staranchuk on 10/21/16
 */

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ScorocodeSdkTestUserClass {

    public static final String TEST_USER_NAME = "testUserName1";
    public static final String TEST_USER_EMAIL = "testUserName1@gmail.com";
    public static final String TEST_USER_PASSWORD = "qwerty";

    @BeforeClass
    public static void setUp() throws Exception {
        ScorocodeSdk.initWith(APP_ID, CLIENT_KEY, MASTER_KEY, null, null, null, null);

        Query query = new Query("users");
        query.equalTo("testUser", true);

        final CountDownLatch countDownLatch = new CountDownLatch(1);
        query.removeDocument(new CallbackRemoveDocument() {
            @Override
            public void onRemoveSucceed(ResponseRemove responseRemove) {
                countDownLatch.countDown();
            }

            @Override
            public void onRemoveFailed(String errorCode, String errorMessage) {
                countDownLatch.countDown();
            }
        });

        countDownLatch.await();
    }

    @Test
    public void test1UserRegisterWithDocument() throws InterruptedException {
        User user = new User();

        Document document = new Document("USERS");
        document.setField("testUser", true);

        final CountDownLatch countDownLatch = new CountDownLatch(1);

        user.register(TEST_USER_NAME, TEST_USER_EMAIL, TEST_USER_PASSWORD, document.getDocumentContent(), new CallbackRegisterUser() {
            @Override
            public void onRegisterSucceed() {
                countDownLatch.countDown();
            }

            @Override
            public void onRegisterFailed(String errorCode, String errorMessage) {
                printError("не удалось зарегистрировать тестового пользователя", errorCode, errorMessage);
                countDownLatch.countDown();
            }
        });

        countDownLatch.await();
    }

    @Test
    public void test2UserLogin() throws InterruptedException {
        assertNull("SessionId уже был установлен. Условия теста не выполняются", ScorocodeSdk.getSessionId());

        final CountDownLatch countDownLatch = new CountDownLatch(1);

        User user = new User();
        user.login(TEST_USER_EMAIL, TEST_USER_PASSWORD, new CallbackLoginUser() {
            @Override
            public void onLoginSucceed(ResponseLogin responseLogin) {
                assertNotNull("sessionId не был установлен", ScorocodeSdk.getSessionId());
                countDownLatch.countDown();
            }

            @Override
            public void onLoginFailed(String errorCode, String errorMessage) {
                printError("не удалось начать активную сессию юзера.", errorCode, errorMessage);
                countDownLatch.countDown();
            }
        });

        countDownLatch.await();
    }

    @Test
    public void test3UserLogout() throws InterruptedException {
        assertNotNull("sessionId не был установлен", ScorocodeSdk.getSessionId());

        final CountDownLatch countDownLatch = new CountDownLatch(1);
        User user = new User();
        user.logout(new CallbackLogoutUser() {
            @Override
            public void onLogoutSucceed() {
                countDownLatch.countDown();
            }

            @Override
            public void onLogoutFailed(String errorCode, String errorMessage) {
                printError("не удалось завершить активную сессию пользователя", errorCode, errorMessage);
                countDownLatch.countDown();
            }
        });

        countDownLatch.await();
    }


}
