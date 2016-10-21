package ru.profit_group.scorocode_sdk;

import static org.junit.Assert.fail;

/**
 * Created by Peter Staranchuk on 10/21/16.
 */

public class ScorocodeTestHelper {
    public static final String APP_ID = "305ffd6cc32832f6819bf4e4f4707848";
    public static final String CLIENT_KEY = "962066371eefc0d1850a76c7ab14c1dc";
    public static final String MASTER_KEY = "383499df2748bb4560745d5da67f5e41";
    static final String TEST_COLLECTION_NAME = "testscorocodesdkcollection";

    static void printError(String message, String errorCodes, String errorMessage) {
        fail(message + " Error Code: " + errorCodes + " Error message: " + errorMessage);
    }
}
