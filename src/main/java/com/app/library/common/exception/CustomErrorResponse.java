package com.app.library.common.exception;

import java.time.LocalDateTime;

public class CustomErrorResponse {
    private String code;
    private String path;
    private String message;
    private String requestId;
    private LocalDateTime dateTime;

    public CustomErrorResponse() {

    }

    public CustomErrorResponse(String message, String code, LocalDateTime dateTime, String path, String requestId) {
        this.message = message;
        this.code = code;
        this.dateTime = dateTime;
        this.path = path;
        this.requestId = requestId;
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

}
