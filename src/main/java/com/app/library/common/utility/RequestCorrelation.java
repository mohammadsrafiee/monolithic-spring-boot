package com.app.library.common.utility;

public class RequestCorrelation {
    public static final String CORRELATION_ID = "correlationId";
    public static final String CORRELATION_ID_HEADER_NAME = "X-Correlation-Id";
    private ThreadLocal<String> id = new ThreadLocal<>();
    private static final ThreadLocal<RequestCorrelation> context = new ThreadLocal<>();

    public static RequestCorrelation getRequestCorrelation() {
        RequestCorrelation result = context.get();
        if (result == null) {
            result = new RequestCorrelation();
            context.set(result);
        }
        return result;
    }

    public String getId() {
        return id.get();
    }

    public void setId(String correlationId) {
        id.set(correlationId);
    }

}
