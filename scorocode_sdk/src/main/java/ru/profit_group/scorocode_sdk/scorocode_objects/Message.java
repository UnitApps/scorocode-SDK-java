package ru.profit_group.scorocode_sdk.scorocode_objects;

import java.io.Serializable;

import retrofit2.Callback;
import ru.profit_group.scorocode_sdk.Callbacks.CallbackSendEmail;
import ru.profit_group.scorocode_sdk.Callbacks.CallbackSendPush;
import ru.profit_group.scorocode_sdk.Callbacks.CallbackSendSms;
import ru.profit_group.scorocode_sdk.Requests.messages.MessageEmail;
import ru.profit_group.scorocode_sdk.Requests.messages.MessagePush;
import ru.profit_group.scorocode_sdk.Requests.messages.MessageSms;
import ru.profit_group.scorocode_sdk.Responses.ResponseCodes;
import ru.profit_group.scorocode_sdk.ScorocodeSdk;

/**
 * Created by Peter Staranchuk on 10/7/16
 */

public class Message implements Serializable {

    public void sendEmail(MessageEmail messageEmail, Query query, CallbackSendEmail callback) throws IllegalStateException {
        String coll = query.getCollectionName();
        ScorocodeSdk.sendEmail(coll, query, messageEmail, callback);
    }

    public void sendEmail(MessageEmail messageEmail, CallbackSendEmail callback) throws IllegalStateException {
        ScorocodeSdk.sendEmail("users", null, messageEmail, callback);
    }

    public void sendPush(MessagePush messagePush, Query query, CallbackSendPush callback) throws IllegalStateException {
        ScorocodeSdk.sendPush(query.getCollectionName(), query, messagePush, callback);
    }

    public void sendPush(MessagePush messagePush, CallbackSendPush callback) throws IllegalStateException {
        ScorocodeSdk.sendPush("users", null, messagePush, callback);
    }

    public void sendSms(MessageSms messageSms, Query query, CallbackSendSms callback) throws IllegalStateException {
        ScorocodeSdk.sendSms(query.getCollectionName(), query, messageSms, callback);
    }

    public void sendSms(MessageSms messageSms, CallbackSendSms callback) throws IllegalStateException {
        ScorocodeSdk.sendSms("users", null, messageSms, callback);
    }

}
