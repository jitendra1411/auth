package org.example.auth.exception;


import lombok.Builder;
import lombok.Getter;

/**
 * @author Jitendra (Jeetu)
 */


@Builder
@Getter
public final class ServiceError {
    private final int status;
    private final String message;
    private final String code;

    private ServiceError(int status, String message, String code) {
        this.status = status;
        this.message = message;
        this.code = code;
    }

    public String toString() {
        return "ServiceError{status=" + this.status + ", message='" + this.message + '\'' + '}';
    }
}