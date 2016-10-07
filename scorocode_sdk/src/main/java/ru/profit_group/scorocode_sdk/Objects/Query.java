package ru.profit_group.scorocode_sdk.Objects;

import java.util.HashMap;

/**
 * Created by Peter Staranchuk on 25/09/16
 */
public class Query extends HashMap<String, HashMap<String,String>> {

    public Query() {}

    public Query(String field, String operator, String value) {
        HashMap<String,String> query = new HashMap<>();
        query.put(operator, value);

        this.put(field, query);
    }
}
