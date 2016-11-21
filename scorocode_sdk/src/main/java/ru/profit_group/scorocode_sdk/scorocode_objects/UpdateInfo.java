package ru.profit_group.scorocode_sdk.scorocode_objects;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Peter Staranchuk on 10/17/16
 */

public class UpdateInfo implements Serializable {
    Map<String, HashMap<String, Object>> info;

    public UpdateInfo() {
        this.info = new HashMap<>();
    }

    public UpdateInfo(Map<String, HashMap<String, Object>> info) {
        this.info = info;
    }

    public Map<String, HashMap<String, Object>> getInfo() {
        return info;
    }
}
