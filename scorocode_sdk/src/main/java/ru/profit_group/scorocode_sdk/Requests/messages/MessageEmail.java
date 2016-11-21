package ru.profit_group.scorocode_sdk.Requests.messages;

/**
 * Created by peter on 30/09/2016.
 */

public class MessageEmail {
    private String subject;
    private String text;
    private String from;

    public MessageEmail(String from, String subject, String text) {
        this.subject = subject;
        this.text = text;
        this.from = from;
    }
}
