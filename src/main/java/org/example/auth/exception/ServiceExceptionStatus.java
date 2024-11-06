package org.example.auth.exception;
import org.apache.logging.log4j.util.Strings;

/**
 * @author Jitendra (Jeetu)
 */


public class ServiceExceptionStatus {

    public static ServiceException statusMap(int status, String errorCode, String errorMsg) {
        if (Strings.isEmpty(errorMsg)) {
            errorMsg = Errors.errorMap.get(Errors.API_BAD_SERVICE_ERROR);
        }
        switch (status) {
            case 400:
                return ServiceException.badRequest(errorCode, errorMsg);
            case 404:
                return ServiceException.notFound(errorCode, errorMsg);
            case 409:
                return ServiceException.conflict(errorCode, errorMsg);
            case 424:
                return ServiceException.externalError(errorCode, errorMsg);
            default:
                return ServiceException.internalError(errorCode, errorMsg);
        }
    }
}