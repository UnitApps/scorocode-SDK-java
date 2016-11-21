package ru.profit_group.scorocode_sdk.scorocode_objects;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Peter Staranchuk on 25/09/16
 */
public class SortInfo implements Serializable {
    private Map<String, Integer> info;

    public SortInfo() {
        this.info = new HashMap<>();
    }

    public SortInfo(HashMap<String, Integer> values) {
        this.info = values;
    }

    public void setAscendingFor(String fieldName) {
        info.put(fieldName, 1);
    }

    public void setDescendingFor(String fieldName) {
        info.put(fieldName, -1);
    }

    public Map<String, Integer> getInfo() {
        if(info == null) {
            return new HashMap<>();
        }
        return info;
    }
}
