package com.app.library.common.exception;

public class BusinessException extends RuntimeException {
    private final String code;
    private final Integer httpStatusCode;

    public BusinessException() {
        super();
        code = null;
        httpStatusCode = null;
    }

    public BusinessException(String message) {
        this(message, null, null, null);
    }

    public BusinessException(String message, String code) {
        this(message, null, code, null);
    }

    public BusinessException(String message, Throwable cause) {
        this(message, cause, null, null);
    }

    public BusinessException(String message, Throwable cause, String code, Integer httpStatusCode) {
        super(message, cause);
        this.code = code;
        this.httpStatusCode = httpStatusCode;
    }

    public BusinessException(Throwable cause) {
        this(null, cause, null, null);
    }

    public BusinessException(Throwable cause, String code) {
        this(null, cause, code, null);
    }

    public String getCode() {
        return code;
    }

    public Integer getHttpStatusCode() {
        return httpStatusCode;
    }
}
