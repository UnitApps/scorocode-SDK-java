package ru.profit_group.scorocode_sdk.Objects;

import java.util.HashMap;

/**
 * Created by peter on 25/09/16.
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
