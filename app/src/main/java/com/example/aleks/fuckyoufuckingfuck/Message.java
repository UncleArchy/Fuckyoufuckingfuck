package com.example.aleks.fuckyoufuckingfuck;

import java.util.Date;

public class Message {
    private String textMessage;
    private String authorMessage;
    private long timeMessage;

    public Message(String textMessage, String authorMessage) {
        this.textMessage = textMessage;
        this.authorMessage = authorMessage;

        timeMessage = new Date().getTime();
    }

    public String getTextMessage() {
        return textMessage;
    }

    public void setTextMessage(String textMessage) {
        this.textMessage = textMessage;
    }

    public String getAuthorMessage() {
        return authorMessage;
    }

    public void setAuthorMessage(String authorMessage) {
        this.authorMessage = authorMessage;
    }

    public long getTimeMessage() {
        return timeMessage;
    }

    public void setTimeMessage(long timeMessage) {
        this.timeMessage = timeMessage;
    }
}
