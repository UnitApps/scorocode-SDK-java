package ru.profit_group.scorocode_sdk.Objects;

import java.util.HashMap;

/**
 * Created by peter on 30/09/2016.
 */

public class MessagePush {
    private String text;
    private HashMap<String, String> data;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public HashMap<String, String> getData() {
        return data;
    }

    public void setData(HashMap<String, String> data) {
        this.data = data;
    }
}
