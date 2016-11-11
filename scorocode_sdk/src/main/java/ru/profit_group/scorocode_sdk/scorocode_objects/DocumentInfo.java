package ru.profit_group.scorocode_sdk.scorocode_objects;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Peter Staranchuk on 10/17/16
 */

public class DocumentInfo implements Serializable {

    private static final String ID_KEY = "_id";
    private HashMap<String, Object> content;

    public DocumentInfo() {
        content = new HashMap<>();
    }

    public DocumentInfo(HashMap<String, Object> documentContent) {
        content = new HashMap<>();
        if(documentContent != null) {
            content.putAll(documentContent);
        }
    }

    public String getId() {
        return (String) content.get(ID_KEY);
    }

    public HashMap<String, Object> getFields() {
        HashMap<String, Object> fields = new HashMap<>();
        for(String key : content.keySet()) {
            if(!key.equalsIgnoreCase(ID_KEY)) {
                fields.put(key, content.get(key));
            }
        }

        return fields;
    }

    public Object get(String key) {
        if(content != null) {
            return content.get(key);
        } else {
            return null;
        }
    }

    public void put(String field, Object value) {
        if(content != null) {
            content.put(field, value);
        }
    }

    public HashMap<String, Object> getContent() {
        return content;
    }
}
