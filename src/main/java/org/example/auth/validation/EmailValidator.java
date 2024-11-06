package org.example.auth.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.util.StringUtils;

/**
 * @author Jitendra (Jeetu)
 */


public class EmailValidator implements ConstraintValidator<ValidEmail, String> {
    @Override
    public void initialize(ValidEmail email) {
    }

    @Override
    public boolean isValid(String contactField,
                           ConstraintValidatorContext cxt) {
        if(!StringUtils.hasText(contactField)){
            return true;
        }
        if (contactField.endsWith(".")){
            return false;
        }
        return contactField.matches("^\\s*?(.+)@(.+?)\\s*$");
    }
}
