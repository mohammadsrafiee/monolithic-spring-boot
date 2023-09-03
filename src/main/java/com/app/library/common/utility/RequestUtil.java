package com.app.library.common.utility;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class RequestUtil {

    private RequestUtil() {
        
    }

    public static String getClientIpAddress(HttpServletRequest request) {
        if (request != null) {
            List<String> headerCandidates = Arrays.asList("X-Forwarded-For",
                    "Proxy-Client-IP",
                    "WL-Proxy-Client-IP",
                    "HTTP_X_FORWARDED_FOR",
                    "HTTP_X_FORWARDED",
                    "HTTP_X_CLUSTER_CLIENT_IP",
                    "HTTP_CLIENT_IP",
                    "HTTP_FORWARDED_FOR",
                    "HTTP_FORWARDED",
                    "HTTP_VIA",
                    "REMOTE_ADDR");
            Optional<String> ipAddress = headerCandidates
                    .stream()
                    .map(request::getHeader)
                    .filter(ip -> ip != null && !ip.isEmpty() && !"unknown".equalsIgnoreCase(ip))
                    .findFirst();
            return ipAddress.orElseGet(request::getRemoteAddr);
        }
        return "";
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
