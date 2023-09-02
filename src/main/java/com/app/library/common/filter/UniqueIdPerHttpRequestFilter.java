package com.app.library.common.filter;

public class UniqueIdPerHttpRequestFilter {
    // TODO complete it
//        // implements Filter {
//
//    @Override
//    public void doFilter(ServletRequest servletRequest,
//                         ServletResponse servletResponse,
//                         FilterChain filterChain) throws IOException, ServletException {
//        final HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
//        try {
//            String uniqueId = httpServletRequest.getHeader(RequestCorrelation.CORRELATION_ID_HEADER_NAME);
//            if (!currentRequestIsAsyncDispatcher(httpServletRequest)) {
//                if ((uniqueId == null) || (uniqueId.isEmpty())) {
//                    uniqueId = UUID.randomUUID().toString();
//                }
//                RequestCorrelation.getRequestCorrelation().setId(uniqueId);
//            }
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//        filterChain.doFilter(httpServletRequest, servletResponse);
//    }
//
//    private boolean currentRequestIsAsyncDispatcher(HttpServletRequest httpServletRequest) {
//        return httpServletRequest.getDispatcherType().equals(DispatcherType.ASYNC);
//    }
}