package ru.profit_group.scorocode_sdk.Objects;

/**
 * Created by peter on 30/09/2016.
 */

public class MessageSms {
    private String text;

    public MessageSms(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
