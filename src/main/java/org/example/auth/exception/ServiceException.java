package org.example.auth.exception;

import java.text.MessageFormat;

import static org.springframework.http.HttpStatus.*;

/**
 * @author Jitendra (Jeetu)
 */


public final class ServiceException extends RuntimeException {
    private final ServiceError serviceError;

    public ServiceException(ServiceError serviceError) {
        super(serviceError.getMessage());
        this.serviceError = serviceError;
    }

    public static ServiceException badRequest(String code, String message, Object... args) {
        return new ServiceException(ServiceError
                .builder()
                .status(BAD_REQUEST.value())
                .message(MessageFormat.format(message, args))
                .code(code)
                .build());
    }

    public static ServiceException unauthorizedRequest(String code, String message, Object... args) {
        return new ServiceException(ServiceError
                .builder()
                .status(UNAUTHORIZED.value())
                .message(MessageFormat.format(message, args))
                .code(code)
                .build());
    }

    public static ServiceException notFound(String code, String message, Object... args) {
        return new ServiceException(ServiceError
                .builder()
                .status(NOT_FOUND.value())
                .message(MessageFormat.format(message, args))
                .code(code)
                .build());
    }

    public static ServiceException conflict(String code, String message, Object... args) {
        return new ServiceException(ServiceError
                .builder()
                .status(CONFLICT.value())
                .message(MessageFormat.format(message, args))
                .code(code)
                .build());
    }

    public static ServiceException internalError(String code, String message, Object... args) {
        return new ServiceException(ServiceError
                .builder()
                .status(INTERNAL_SERVER_ERROR.value())
                .message(MessageFormat.format(message, args))
                .code(code)
                .build());
    }

    public static ServiceException externalError(String code, String message, Object... args) {
        return new ServiceException(ServiceError
                .builder()
                .status(FAILED_DEPENDENCY.value())
                .message(MessageFormat.format(message, args))
                .code(code)
                .build());
    }

    public static ServiceException forbidden(String code, String message, Object... args) {
        return new ServiceException(ServiceError
                .builder()
                .status(FORBIDDEN.value())
                .message(MessageFormat.format(message, args))
                .code(code)
                .build());
    }

    public static ServiceException serviceUnavailable(String code, String message, Object... args) {
        return new ServiceException(ServiceError
                .builder()
                .status(SERVICE_UNAVAILABLE.value())
                .message(MessageFormat.format(message, args))
                .code(code)
                .build());
    }

    public ServiceError serviceError() {
        return this.serviceError;
    }

    public String toString() {
        return "ServiceException{serviceError=" + this.serviceError + '}';
    }
}
