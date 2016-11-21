package ru.profit_group.scorocode_sdk.scorocode_objects;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import ru.profit_group.scorocode_sdk.Responses.ResponseCodes;

/**
 * Created by Peter Staranchuk on 11/21/16
 */

public class NetworkHelper {
    public static String readUrl(String urlString) throws Exception {
        BufferedReader reader = null;
        try {
            URL url = new URL(urlString);
            reader = new BufferedReader(new InputStreamReader(url.openStream()));
            StringBuffer buffer = new StringBuffer();
            int read;
            char[] chars = new char[1024];
            while ((read = reader.read(chars)) != -1)
                buffer.append(chars, 0, read);

            return buffer.toString();
        } finally {
            if (reader != null)
                reader.close();
        }
    }

    public static boolean isResponseSucceed(ResponseCodes responseCodes) {
        return !responseCodes.isError();
    }
}
