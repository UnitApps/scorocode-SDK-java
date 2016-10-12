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

    public void sendEmail(Query query, MessageEmail messageEmail, CollectionName collectionName, CallbackSendEmail callback) throws IllegalStateException {

        ScorocodeSdk.sendEmail(collectionName.getCollectionName(), query, messageEmail, callback);
    }

    public void sendPush(Query query, MessagePush messagePush, CollectionName collectionName, CallbackSendPush callback) throws IllegalStateException {
        ScorocodeSdk.sendPush(collectionName.getCollectionName(), query, messagePush, callback);
    }

    public void sendSms(Query query, MessageSms messageSms, CollectionName collectionName, CallbackSendSms callback) throws IllegalStateException {
        ScorocodeSdk.sendSms(collectionName.getCollectionName(), query, messageSms, callback);
    }

}
