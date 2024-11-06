package org.example.auth.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.util.Strings;
import org.example.auth.util.ThreadLocalUtils;
import org.slf4j.MDC;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.UUID;

/**
 * @author Jitendra (Jeetu)
 */
public class IncomingInterceptor implements HandlerInterceptor {
    private static final String CORRELATION_ID_REQUEST_HEADER_NAME = "X-Correlation-Id";
    private static final String CORRELATION_ID_RESPONSE_HEADER_NAME = "correlationId";
    public static final String CORRELATION_ID_LOG_VAR_NAME = "correlationId";
    private static final String USER_ID = "X-User-Id";
    private static final String ROLE = "X-USER-ROLE";


    @Override
    public boolean preHandle(final HttpServletRequest request, final HttpServletResponse response,
                             final Object handler) throws Exception {
        //TODO these code are not running
        final String correlationId = getCorrelationIdFromHeader(request);
        MDC.put(CORRELATION_ID_LOG_VAR_NAME, correlationId);
        response.setHeader(CORRELATION_ID_RESPONSE_HEADER_NAME, correlationId);

        ThreadLocalUtils.setThreadLocalUser(getUserIdFromHeader(request));
        ThreadLocalUtils.setThreadLocalRole(getRole(request));

        return true;
    }


    private String getCorrelationIdFromHeader(final HttpServletRequest request) {
        String correlationId = request.getHeader(CORRELATION_ID_REQUEST_HEADER_NAME);
        if (Strings.isBlank(correlationId)) {
            correlationId = UUID.randomUUID().toString();
        }

        return correlationId;
    }

    private Long getUserIdFromHeader(final HttpServletRequest request) {
        String userIdStr = request.getHeader(USER_ID);
        Long userId = null;
        if (!Strings.isBlank(userIdStr)) {
            userId = Long.parseLong(userIdStr);
        }
        return userId;
    }

    private String getRole(final HttpServletRequest request) {
        return request.getHeader(ROLE);
    }
}
