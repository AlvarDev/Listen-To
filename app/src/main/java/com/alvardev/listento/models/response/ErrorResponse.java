package com.alvardev.listento.models.response;

import java.util.List;

/**
 * Created by alvardev on 21/05/17.
 * For manage error response from server
 */

public class ErrorResponse {

    private List<String> messages;

    public ErrorResponse() {
    }

    public ErrorResponse(List<String> messages) {
        this.messages = messages;
    }

    public List<String> getMessages() {
        return messages;
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }

    @Override
    public String toString() {
        return "ErrorResponse{" +
                "messages=" + messages +
                '}';
    }
}