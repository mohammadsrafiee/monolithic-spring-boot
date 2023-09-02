package com.app.library.common.utility;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Enumeration;

public class RequestUtil {
    private static final String[] IP_HEADER_CANDIDATES = {
            "X-FORWARDED-FOR",
            "Forwarded",
            "VIA",
    };

    public static String getClientIpAddress(HttpServletRequest request) {
        String result = "";
        if (request != null) {
            Enumeration<String> headerNames = request.getHeaderNames();
            while (headerNames.hasMoreElements()) {
                String s = headerNames.nextElement();
                for (String header : IP_HEADER_CANDIDATES) {
                    if (s.equalsIgnoreCase(header.toUpperCase())) {
                        String ip = request.getHeader(s);
                        if ((ip != null) && (ip.length() != 0) && !("unknown".equalsIgnoreCase(ip))) {
                            return ip;
                        }
                    }
                }
            }
            result = request.getRemoteAddr();
        }
        return result;
    }

    public static HttpServletRequest getCurrentRequest() {
        HttpServletRequest result = null;
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes != null) {
            result = ((ServletRequestAttributes) requestAttributes).getRequest();

        }
        return result;
    }
}
