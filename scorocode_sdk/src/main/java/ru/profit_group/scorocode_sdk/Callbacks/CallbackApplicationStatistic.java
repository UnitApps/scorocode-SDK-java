package ru.profit_group.scorocode_sdk.Callbacks;

import ru.profit_group.scorocode_sdk.Responses.statistic.ResponseAppStatistic;

/**
 * Created by Peter Staranchuk on 10/12/16
 */

public interface CallbackApplicationStatistic {
    void onRequestSucceed(ResponseAppStatistic appStatistic);
    void onRequestFailed(String errorCode, String errorMessage);
}
