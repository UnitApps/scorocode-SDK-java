package ru.profit_group.scorocode_sdk.scorocode_objects;

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

public class Message {

    public void sendEmail(MessageEmail messageEmail, CollectionName collectionName, Query query, CallbackSendEmail callback) throws IllegalStateException {
        ScorocodeSdk.sendEmail(collectionName.getCollectionName(), query, messageEmail, callback);
    }

    public void sendEmail(MessageEmail messageEmail, CollectionName collectionName, CallbackSendEmail callback) throws IllegalStateException {
        ScorocodeSdk.sendEmail(collectionName.getCollectionName(), null, messageEmail, callback);
    }

    public void sendEmail(MessageEmail messageEmail, CallbackSendEmail callback) throws IllegalStateException {
        ScorocodeSdk.sendEmail(CollectionName.USERS.getCollectionName(), null, messageEmail, callback);
    }

    public void sendPush(MessagePush messagePush, CollectionName collectionName, Query query, CallbackSendPush callback) throws IllegalStateException {
        ScorocodeSdk.sendPush(collectionName.getCollectionName(), query, messagePush, callback);
    }

    public void sendPush(MessagePush messagePush, CollectionName collectionName, CallbackSendPush callback) throws IllegalStateException {
        ScorocodeSdk.sendPush(collectionName.getCollectionName(), null, messagePush, callback);
    }

    public void sendPush(MessagePush messagePush, CallbackSendPush callback) throws IllegalStateException {
        ScorocodeSdk.sendPush(CollectionName.USERS.getCollectionName(), null, messagePush, callback);
    }

    public void sendSms(MessageSms messageSms, CollectionName collectionName, Query query, CallbackSendSms callback) throws IllegalStateException {
        ScorocodeSdk.sendSms(collectionName.getCollectionName(), query, messageSms, callback);
    }

    public void sendSms(MessageSms messageSms, CollectionName collectionName, CallbackSendSms callback) throws IllegalStateException {
        ScorocodeSdk.sendSms(collectionName.getCollectionName(), null, messageSms, callback);
    }

    public void sendSms(MessageSms messageSms, CallbackSendSms callback) throws IllegalStateException {
        ScorocodeSdk.sendSms(CollectionName.USERS.getCollectionName(), null, messageSms, callback);
    }

}
