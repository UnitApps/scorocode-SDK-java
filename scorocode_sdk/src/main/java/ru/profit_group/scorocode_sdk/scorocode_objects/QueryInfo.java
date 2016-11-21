package ru.profit_group.scorocode_sdk.scorocode_objects;

import android.support.annotation.NonNull;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Peter Staranchuk on 10/13/16
 */
public class QueryInfo implements Serializable {
    private Map<String, Object> info;

    public QueryInfo() {
        this.info = new HashMap<>();
    }

    public QueryInfo(Map<String, Object> info) {
        this.info = info;
    }

    @NonNull
    public Map<String, Object> getInfo() {
        if(info == null) {
            return new HashMap<>();
        }
        return info;
    }
}
