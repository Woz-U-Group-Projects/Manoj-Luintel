package com.example.groupproject.Exception;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.util.List;

public class ErrorResponse {
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "UTC")
    private LocalDateTime timestamp;
    private int messageCode;
    private String message;
    private boolean error;
    private List<?> objects;

    public ErrorResponse() {
    }

    public ErrorResponse(String message, int messageCode) {
        this.timestamp = LocalDateTime.now();
        this.messageCode = messageCode;
        this.message = message;
        this.error = true;
        this.objects = null;
    }

    public ErrorResponse(String message){
        this.timestamp = LocalDateTime.now();
        this.messageCode = 99;
        this.message = message;
        this.error = true;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public ErrorResponse setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    public int getMessageCode() {
        return messageCode;
    }

    public ErrorResponse setMessageCode(int messageCode) {
        this.messageCode = messageCode;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public ErrorResponse setMessage(String message) {
        this.message = message;
        return this;
    }

    public boolean isError() {
        return error;
    }

    public ErrorResponse setError(boolean error) {
        this.error = error;
        return this;
    }

    public List<?> getObjects() {
        return objects;
    }

    public ErrorResponse setObjects(List<?> objects) {
        this.objects = objects;
        return this;
    }
}
