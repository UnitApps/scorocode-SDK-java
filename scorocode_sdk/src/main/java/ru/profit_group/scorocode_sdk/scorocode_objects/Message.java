package ru.profit_group.scorocode_sdk.scorocode_objects;

import retrofit2.Callback;
import ru.profit_group.scorocode_sdk.Requests.messages.MessageEmail;
import ru.profit_group.scorocode_sdk.Requests.messages.MessagePush;
import ru.profit_group.scorocode_sdk.Requests.messages.MessageSms;
import ru.profit_group.scorocode_sdk.Responses.ResponseCodes;
import ru.profit_group.scorocode_sdk.ScorocodeSdk;

/**
 * Created by Peter Staranchuk on 10/7/16
 */

public class Message {

    public void sendEmail(Query query, MessageEmail messageEmail, CollectionName collectionName, Callback<ResponseCodes> callback) throws IllegalStateException {

        ScorocodeSdk.sendEmail(ScorocodeSdk.getAppId(), ScorocodeSdk.getClientKey(),
                ScorocodeSdk.getAccountKey(), ScorocodeSdk.getSessionId(), collectionName.getCollectionName(), query, messageEmail, callback);
    }

    public void sendPush(Query query, MessagePush messagePush, CollectionName collectionName, Callback<ResponseCodes> callback) throws IllegalStateException {
        ScorocodeSdk.sendPush(ScorocodeSdk.getAppId(), ScorocodeSdk.getClientKey(),
                ScorocodeSdk.getAccountKey(), ScorocodeSdk.getSessionId(), collectionName.getCollectionName(), query, messagePush, callback);
    }

    public void sendSms(Query query, MessageSms messageSms, CollectionName collectionName, Callback<ResponseCodes> callback) throws IllegalStateException {
        ScorocodeSdk.sendSms(ScorocodeSdk.getAppId(), ScorocodeSdk.getClientKey(),
                ScorocodeSdk.getAccountKey(), ScorocodeSdk.getSessionId(), collectionName.getCollectionName(), query, messageSms, callback);
    }

}
