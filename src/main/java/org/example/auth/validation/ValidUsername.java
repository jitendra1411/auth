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
@Constraint(validatedBy = UsernameValidator.class)
public @interface ValidUsername {
    String message() default "username must be min 3 character and max 32 character, only alphanumeric and underscore(_) allowed";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
