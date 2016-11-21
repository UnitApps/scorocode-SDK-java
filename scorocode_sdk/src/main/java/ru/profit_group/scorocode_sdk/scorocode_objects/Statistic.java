package ru.profit_group.scorocode_sdk.scorocode_objects;

import java.io.Serializable;

import ru.profit_group.scorocode_sdk.Callbacks.CallbackApplicationStatistic;
import ru.profit_group.scorocode_sdk.Responses.statistic.ResponseAppStatistic;
import ru.profit_group.scorocode_sdk.ScorocodeSdk;

/**
 * Created by Peter Staranchuk on 10/21/16
 */

public class Statistic implements Serializable {

    public void getApplicationStatistic(CallbackApplicationStatistic callbackApplicationStatistic) {
        ScorocodeSdk.getApplicationStatistic(callbackApplicationStatistic);
    }
}
