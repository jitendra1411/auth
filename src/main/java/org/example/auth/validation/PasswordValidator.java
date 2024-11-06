package org.example.auth.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * @author Jitendra (Jeetu)
 */


public class PasswordValidator implements ConstraintValidator<ValidPassword, String> {
    private boolean required;

    @Override
    public void initialize(ValidPassword constraintAnnotation) {
        this.required = constraintAnnotation.required();
    }

    @Override
    public boolean isValid(String password, ConstraintValidatorContext constraintValidatorContext) {
        if(password == null){
            return !required;
        }
        return password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%&*()-+=^]).{8,}");
    }
}
