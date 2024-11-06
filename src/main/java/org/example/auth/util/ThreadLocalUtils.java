package org.example.auth.util;


import org.example.auth.exception.Errors;
import org.example.auth.exception.ServiceException;

/**
 * @author Jitendra (Jeetu)
 */
public class ThreadLocalUtils {
    private static ThreadLocal<Long> threadLocalUser = new ThreadLocal<>();
    private static ThreadLocal<String> threadLocalRole = new ThreadLocal<>();

    public static String getThreadLocalRole() {
        return threadLocalRole.get();
    }
    public static void setThreadLocalRole(String role) {
        threadLocalRole.set(role);
    }

    public static Long getThreadLocalUser() {
        return threadLocalUser.get();
    }

    public static Long getThreadLocalUserWithExc() {
        if(threadLocalUser.get() == null) {
            throw ServiceException.unauthorizedRequest(Errors.UNAUTHORIZED_ACCESS,
                    Errors.errorMap.get(Errors.UNAUTHORIZED_ACCESS));
        }
        return threadLocalUser.get();
    }
    public static void setThreadLocalUser(Long userIdReq) {
        threadLocalUser.set(userIdReq);
    }

    public static void remove() {
        threadLocalUser.remove();
        threadLocalRole.remove();
    }
}
