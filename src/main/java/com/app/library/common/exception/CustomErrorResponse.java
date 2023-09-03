package com.app.library.common.exception;

import java.time.LocalDateTime;

public class CustomErrorResponse {
    private String code;
    private String path;
    private String message;
    private String devMessage;
    private String requestId;
    private LocalDateTime dateTime;

    public CustomErrorResponse() {

    }

    public CustomErrorResponse(String code,
                               String path,
                               String message,
                               String devMessage,
                               String requestId,
                               LocalDateTime dateTime) {
        this.code = code;
        this.path = path;
        this.message = message;
        this.devMessage = devMessage;
        this.requestId = requestId;
        this.dateTime = dateTime;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getDevMessage() {
        return devMessage;
    }

    public void setDevMessage(String devMessage) {
        this.devMessage = devMessage;
    }
}
