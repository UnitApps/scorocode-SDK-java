package ru.profit_group.scorocode_sdk.Objects;

/**
 * Created by peter on 30/09/2016.
 */

public class MessageEmail {
    private String subject;
    private String text;
    private String from;

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }
}
