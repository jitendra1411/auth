package org.example.auth.exception;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Jitendra (Jeetu)
 */


public interface Errors {

    String API_BAD_SERVICE_ERROR = "ONB-API-001",
            API_ERROR = "ONB-API-002";

    String UNAUTHORIZED_ACCESS = "ONB-ACS-001";  // access


    String USER_ALREADY_EXIST_WITH_PHONE = "USR-UAE-001", //user already exist
            USER_ALREADY_EXIST_WITH_EMAIL = "USR-UAE-002",
            USER_ALREADY_EXIST_WITH_USERNAME = "USR-UAE-003";

    String ROLE_DOES_NOT_EXIST = "ROL-RNE-001";  //role not exist

    String USER_ROLE_NOT_FOUND = "USR-RNE-001";  // user role not exist



    Map<String, String> errorMap = new HashMap<String, String>() {{
        put(API_BAD_SERVICE_ERROR, "Unknown error.");
        put(API_ERROR, "Something went wrong.");

        put(UNAUTHORIZED_ACCESS, "Unauthorized access");

        put(USER_ALREADY_EXIST_WITH_PHONE, "User already exist with given phone");
        put(USER_ALREADY_EXIST_WITH_EMAIL, "User already exist with given email");
        put(USER_ALREADY_EXIST_WITH_USERNAME, "User already exist with given username");

        put(ROLE_DOES_NOT_EXIST, "Role does not exist");

        put(USER_ROLE_NOT_FOUND, "User role not found");
    }};
}
