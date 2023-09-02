package com.app.library.common.exception;

import jakarta.persistence.QueryTimeoutException;
import jakarta.persistence.*;
import org.hibernate.HibernateException;
import org.springframework.dao.*;
import org.springframework.jdbc.datasource.lookup.DataSourceLookupFailureException;
import org.springframework.web.bind.MethodArgumentNotValidException;

import javax.script.ScriptException;

public enum ExceptionMapperType {
    // TODO add all exception in Enum for better exception handling
    _400(400,
            "The request is malformed or contains invalid data.",
            new Class[]{
                    TransactionRequiredException.class,
                    InvalidDataAccessApiUsageException.class,
                    InvalidDataAccessApiUsageException.class,
                    MethodArgumentNotValidException.class,
            }),
    _403(403,
            "Authentication succeeded, but the authenticated user does not have permission to access the resource.",
            new Class<?>[]{
                    PermissionDeniedDataAccessException.class,
                    PermissionDeniedDataAccessException.class,
            }),
    _404(404,
            "The requested resource could not be found on the server.",
            new Class[]{
                    EntityNotFoundException.class,
                    NoResultException.class,
                    NoResultException.class,
            }
    ),
    _409(409,
            "There was a conflict with the current state of the resource, preventing the request from being completed.",
            new Class[]{
                    DataIntegrityViolationException.class,
                    PessimisticLockException.class,
                    OptimisticLockException.class,
                    EntityExistsException.class,
            }),
    _412(412,
            "One or more conditions specified in the request headers were not met.",
            null),
    _422(422,
            "The request contains valid data, but the server cannot process it due to semantic errors (e.g., validation failures).",
            null),
    _500(500,
            "An unexpected error occurred on the server.",
            new Class[]{
                    NonUniqueResultException.class,
                    RollbackException.class,
                    HibernateException.class,
                    CleanupFailureDataAccessException.class,
                    UncategorizedDataAccessException.class,
                    InvalidDataAccessResourceUsageException.class,
                    NonTransientDataAccessResourceException.class,
                    DataRetrievalFailureException.class,
                    ScriptException.class,
                    DataRetrievalFailureException.class,
                    NonTransientDataAccessResourceException.class,
                    InvalidDataAccessResourceUsageException.class,
                    UncategorizedDataAccessException.class,
                    NonTransientDataAccessException.class,
            }),
    _503(503,
            "The server is temporarily unavailable, typically due to maintenance or overloading.",
            new Class[]{
                    LockTimeoutException.class,
                    QueryTimeoutException.class,
                    DataSourceLookupFailureException.class,
                    RecoverableDataAccessException.class,
                    TransientDataAccessException.class,
            }),
    _504(504,
            "The server, acting as a gateway, did not receive a timely response from the upstream server or application.",
            null),
    Unknown(500,
            "An unexpected error occurred on the server.",
            null),

    ;

    ExceptionMapperType(int status,
                        String message, // this value just is a default message,
                        // we should better to use I18n or message resources
                        Class<?>[] classes) {
        this.status = status;
        this.message = message;
        this.classes = classes;
    }

    private final Class<?>[] classes;
    private final int status;
    private final String message;

    public Class<?>[] getClasses() {
        return classes;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
