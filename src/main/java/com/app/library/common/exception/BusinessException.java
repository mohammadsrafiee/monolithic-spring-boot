package com.app.library.common.exception;

public class BusinessException extends RuntimeException {
    private String code;
    private Integer httpStatusCode;

    public BusinessException() {
        super();
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

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getHttpStatusCode() {
        return httpStatusCode;
    }

    public void setHttpStatusCode(Integer httpStatusCode) {
        this.httpStatusCode = httpStatusCode;
    }
}
