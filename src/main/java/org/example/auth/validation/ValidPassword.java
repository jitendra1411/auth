package org.example.auth.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author Jitendra (Jeetu)
 */


@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = PasswordValidator.class)
public @interface ValidPassword {
    String message() default "Password must be minimum 8 characters and must contain atleast 1 lowercase alphabet, " +
            "1 uppercase alphabet, 1 digit and 1 special character from !@#$%&*()-+=^";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    boolean required() default true;
}
