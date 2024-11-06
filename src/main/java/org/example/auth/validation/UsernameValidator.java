package org.example.auth.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * @author Jitendra (Jeetu)
 */
public class UsernameValidator implements ConstraintValidator<ValidUsername, String> {
    @Override
    public void initialize(ValidUsername username) {
    }

    @Override
    public boolean isValid(String contactField,
                           ConstraintValidatorContext cxt) {
        if(contactField == null){
            return true;
        }
        return contactField.matches("^[a-zA-Z0-9_]{3,32}$");
    }
}
