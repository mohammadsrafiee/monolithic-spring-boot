package com.app.library.common.filter;

import com.app.library.common.utility.RequestCorrelation;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;
import java.util.UUID;

public class UniqueIdPerHttpRequestFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse response,
                         FilterChain filterChain)
            throws IOException, ServletException {
        final HttpServletRequest request = (HttpServletRequest) servletRequest;
        try {
            String uniqueId = request.getHeader(RequestCorrelation.CORRELATION_ID_HEADER_NAME);
            if (!request.getDispatcherType().equals(DispatcherType.ASYNC)) {
                if ((uniqueId == null) || (uniqueId.isEmpty()))
                    uniqueId = UUID.randomUUID().toString();
                RequestCorrelation.getRequestCorrelation().setId(uniqueId);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        filterChain.doFilter(request, response);
    }
}