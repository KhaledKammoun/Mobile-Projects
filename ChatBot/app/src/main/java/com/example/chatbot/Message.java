package com.example.chatbot;

import java.io.Serializable;

public class Message implements Serializable {
    private  String text ;
    private boolean sendByUser ;
    public Message(String text, boolean sendByUser) {
        this.text = text ;
        this.sendByUser = sendByUser ;
    }
    public String getText() {
        return text ;
    }

    public Boolean getSendByUser() {
        return sendByUser ;
    }
}
