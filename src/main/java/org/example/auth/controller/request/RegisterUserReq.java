package org.example.auth.controller.request;


import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.example.auth.constant.Roles;
import org.example.auth.validation.*;

/**
 * @author Jitendra (Jeetu)
 */


@Data
public class RegisterUserReq {
    @NotBlank(message = "username can't be null or empty")
    @ValidUsername
    private String username;

    @ValidPassword
    private String password;
    private String name;

    @ValidEmail
    private String email;

    @ValidPhone
    private String phone;

    @NotBlank(message = "role can't be null or empty")
    @ValidEnum(enumClass = Roles.class, message = "invalid Role")
    private String role;
}
