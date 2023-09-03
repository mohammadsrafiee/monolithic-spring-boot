package com.app.library.common.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatusCode status,
                                                                  WebRequest request) {
        ExceptionMapperType statusCode = ExceptionMapperType.getStatusCode(ex);
        List<String> errors = ex
                .getBindingResult()
                .getAllErrors()
                .stream()
                .map(ObjectError::getDefaultMessage)
                .toList();
        return generateResponse(new BusinessException(errors.toString(), ex, null, statusCode.getStatus()), request);
    }

    @ExceptionHandler({Throwable.class})
    public ResponseEntity<Object> customHandleException(Throwable ex, WebRequest request) {
        ResponseEntity<Object> response;
        try {
            if (ex instanceof BusinessException) {
                response = generateResponse((BusinessException) ex, request);
            } else if (ex instanceof Exception) {
                response = handleException(((Exception) ex), request);
            } else {
                ExceptionMapperType statusCode = ExceptionMapperType.getStatusCode(ex);
                response = generateResponse(new BusinessException(ex.getMessage(), ex, null, statusCode.getStatus()), request);
            }
        } catch (Exception exception) {
            ExceptionMapperType statusCode = ExceptionMapperType.getStatusCode(exception);
            response = generateResponse(new BusinessException(statusCode.getMessage(), exception, null, statusCode.getStatus()), request);
        }
        return response;
    }

    private ResponseEntity<Object> generateResponse(BusinessException exception, WebRequest request) {
        HttpServletRequest req = ((ServletWebRequest) request).getRequest();
        HttpStatus httpStatus = convertStatusCode(exception.getHttpStatusCode());
        return new ResponseEntity<>(
                new CustomErrorResponse(
                        exception.getCode(),
                        req.getRequestURI(),
                        exception.getMessage(),
                        exception.getCause() != null ? exception.getCause().getMessage() : null,
                        req.getRequestId(),
                        LocalDateTime.now()),
                httpStatus);
    }

    private HttpStatus convertStatusCode(Integer code) {
        HttpStatus result;
        try {
            result = HttpStatus.valueOf(code);
        } catch (Exception ex) {
            result = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return result;
    }
}
