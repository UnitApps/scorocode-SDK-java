package ru.profit_group.scorocode_sdk.Responses.user;

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
         DocumentInfo user;

        public String getSessionId() {
            return sessionId;
        }

        public void setSessionId(String sessionId) {
            this.sessionId = sessionId;
        }

        public DocumentInfo getUserInfo() {
            return user;
        }

        public void setUser(DocumentInfo user) {
            this.user = user;
        }
    }
}
