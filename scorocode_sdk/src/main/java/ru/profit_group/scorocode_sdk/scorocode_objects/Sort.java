package ru.profit_group.scorocode_sdk.scorocode_objects;

import java.util.HashMap;

/**
 * Created by Peter Staranchuk on 25/09/16
 */
public class Sort extends HashMap<String, Integer>{
    private HashMap<String, String> values;

    public HashMap<String, String> getValues() {
        return values;
    }

    public void setValues(HashMap<String, String> values) {
        this.values = values;
    }
}
