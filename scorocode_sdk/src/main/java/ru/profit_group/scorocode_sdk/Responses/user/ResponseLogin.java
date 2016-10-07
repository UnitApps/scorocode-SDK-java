package ru.profit_group.scorocode_sdk.Responses.user;

import java.util.HashMap;

import ru.profit_group.scorocode_sdk.Objects.User;
import ru.profit_group.scorocode_sdk.Responses.ResponseCodes;

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
         User user;

        public String getSessionId() {
            return sessionId;
        }

        public void setSessionId(String sessionId) {
            this.sessionId = sessionId;
        }

        public User getUser() {
            return user;
        }

        public void setUser(User user) {
            this.user = user;
        }
    }
}
