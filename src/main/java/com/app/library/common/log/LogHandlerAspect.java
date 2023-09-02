package com.app.library.common.log;

import com.app.library.common.log.impl.ILogger;
import com.app.library.common.log.impl.LogModel;
import com.app.library.common.utility.RequestCorrelation;
import com.app.library.common.utility.RequestUtil;
import jakarta.annotation.Nullable;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Aspect
@Component
public class LogHandlerAspect {

    private final ILogger logger;
    private static final String THREAD = "thread-";
    private static final String AUDIT_TYPE = "audit";
    private static final String EXCEPTION_TYPE = "exception";

    public LogHandlerAspect(@Autowired(required = false) @Nullable ILogger logger) {
        this.logger = logger;
    }

    @Before("@annotation(com.app.library.common.log.impl.Log)")
    public void before(JoinPoint joinPoint) {
        try {
            if (logger != null) {
                MethodSignature signature = (MethodSignature) joinPoint.getSignature();
                HttpServletRequest request = RequestUtil.getCurrentRequest();
                RequestCorrelation correlation = RequestCorrelation.getRequestCorrelation();
                LogModel log = new LogModel(
                        LocalDateTime.now(),
                        null, // TODO get from security context
                        THREAD + correlation.getId(),
                        RequestUtil.getClientIpAddress(request),
                        AUDIT_TYPE,
                        joinPoint.getKind(),
                        signature.getName(),
                        signature.getDeclaringTypeName(),
                        null,
                        joinPoint.getArgs(), // TODO read from property file and manage for sensitive data
                        null,
                        null
                );
                log.setPretty(true); // TODO read from property file
                logger.writeLog(log);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @AfterThrowing(pointcut = "@annotation(com.app.library.common.log.impl.Log)", throwing = "ex")
    public void exceptionHandling(JoinPoint joinPoint, Throwable ex) {
        try {
            if (logger != null) {
                MethodSignature signature = (MethodSignature) joinPoint.getSignature();
                HttpServletRequest request = RequestUtil.getCurrentRequest();
                RequestCorrelation correlation = RequestCorrelation.getRequestCorrelation();
                LogModel log = new LogModel(
                        LocalDateTime.now(),
                        null, // TODO get from security context
                        THREAD + correlation.getId(),
                        RequestUtil.getClientIpAddress(request),
                        EXCEPTION_TYPE,
                        joinPoint.getKind(),
                        signature.getName(),
                        signature.getDeclaringTypeName(),
                        ex.getMessage(),
                        joinPoint.getArgs(), // TODO read from property file and manage for sensitive data
                        null,
                        getTopOfStackTrace(ex)
                );
                log.setPretty(true); // TODO read from property file
                logger.writeLog(log);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    private Throwable getCause(Throwable throwable) {
        Throwable result = throwable;
        while ((result != null) && (result.getCause() != null)) {
            result = result.getCause();
        }
        return result;
    }

    private StackTraceElement getTopOfStackTrace(Throwable ex) {
        Throwable finalCause = getCause(ex);
        if (finalCause != null) {
            if ((finalCause.getStackTrace() != null) &&
                    (finalCause.getStackTrace().length >= 1)) {
                StackTraceElement[] stackTrace = finalCause.getStackTrace();
                if (stackTrace != null && stackTrace.length > 0)
                    return stackTrace[0];
            }
        }
        return null;
    }
}
