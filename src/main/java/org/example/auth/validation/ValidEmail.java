package org.example.auth.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author Jitendra (Jeetu)
 */


@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = EmailValidator.class)
public @interface ValidEmail {
    String message() default "Invalid email";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
