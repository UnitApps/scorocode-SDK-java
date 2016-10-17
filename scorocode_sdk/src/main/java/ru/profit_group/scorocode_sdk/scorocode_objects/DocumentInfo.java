package ru.profit_group.scorocode_sdk.scorocode_objects;

import java.util.HashMap;

/**
 * Created by Peter Staranchuk on 10/17/16
 */

public class DocumentInfo extends HashMap<String, Object> {

    public static final String ID_KEY = "_id";

    public String getId() {
        return (String) this.get(ID_KEY);
    }

    public HashMap<String, Object> getFields() {
        HashMap<String, Object> fields = new HashMap<>();
        for(String key : this.keySet()) {
            if(!key.equalsIgnoreCase(ID_KEY)) {
                fields.put(key, this.get(key));
            }
        }

        return fields;
    }
}
