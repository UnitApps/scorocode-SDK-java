package ru.profit_group.scorocode_sdk.Responses.user;

import java.util.HashMap;

import ru.profit_group.scorocode_sdk.Responses.ResponseCodes;
import ru.profit_group.scorocode_sdk.scorocode_objects.DocumentInfo;

/**
 * Created by Peter Staranchuk on 5/10/16
 */
public class ResponseLogin extends ResponseCodes {
    private Result result;

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public class Result {
         String sessionId;
        HashMap<String, Object> user;

        public String getSessionId() {
            return sessionId;
        }

        public void setSessionId(String sessionId) {
            this.sessionId = sessionId;
        }

        public DocumentInfo getUserInfo() {
            return new DocumentInfo(user);
        }

        public void setUser(HashMap<String, Object> user) {
            this.user = user;
        }
    }
}
