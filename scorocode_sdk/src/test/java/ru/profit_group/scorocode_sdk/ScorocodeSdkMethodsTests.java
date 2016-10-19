package ru.profit_group.scorocode_sdk;

import android.util.Log;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import ru.profit_group.scorocode_sdk.Callbacks.CallbackApplicationStatistic;
import ru.profit_group.scorocode_sdk.Responses.statistic.ResponseAppStatistic;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ScorocodeSdkMethodsTests {

    private static final String APP_ID = "305ffd6cc32832f6819bf4e4f4707848";
    private static final String CLIENT_KEY = "962066371eefc0d1850a76c7ab14c1dc";
    public static final String MASTER_KEY = "383499df2748bb4560745d5da67f5e41";

    @Before
    public void setUp() throws Exception {
        ScorocodeSdk.initWith(APP_ID, CLIENT_KEY, MASTER_KEY, null, null, null, null);
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
}