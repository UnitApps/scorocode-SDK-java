package ru.profit_group.scorocode_sdk;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;

import ru.profit_group.scorocode_sdk.Callbacks.CallbackApplicationStatistic;
import ru.profit_group.scorocode_sdk.Responses.statistic.ResponseAppStatistic;

/**
 * Created by Peter Staranchuk on 10/20/16
 */

import org.junit.Before;
import org.junit.runners.MethodSorters;

import java.util.concurrent.CountDownLatch;

import static org.junit.Assert.*;
import static ru.profit_group.scorocode_sdk.ScorocodeTestHelper.APP_ID;
import static ru.profit_group.scorocode_sdk.ScorocodeTestHelper.CLIENT_KEY;
import static ru.profit_group.scorocode_sdk.ScorocodeTestHelper.MASTER_KEY;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ScorocodeSdkTestStatisticClass {

    @BeforeClass
    public static void setUp() throws Exception {
        ScorocodeSdk.initWith(APP_ID, CLIENT_KEY, MASTER_KEY, null, null, null, null);
    }

    @Test
    public void test1GetStatistic() throws InterruptedException {
        final CountDownLatch countDownLatch = new CountDownLatch(1);

            ScorocodeSdk.getApplicationStatistic(
                    new CallbackApplicationStatistic() {
                        @Override
                        public void onRequestSucceed(ResponseAppStatistic appStatistic) {
                            assertEquals(false, appStatistic.isError());
                            assertEquals(null, appStatistic.getErrCode());
                            assertEquals(null, appStatistic.getErrMsg());
                            countDownLatch.countDown();
                        }

                        @Override
                        public void onRequestFailed(String errorCode, String errorMessage) {
                            assertNotEquals(null, errorCode);
                            assertNotEquals(null, errorMessage);
                            fail("ошибка при получении статистики");
                            countDownLatch.countDown();
                        }
                    }
            );

        countDownLatch.await();
    }

}
