package org.example.auth.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * @author Jitendra (Jeetu)
 */


public class PhoneNumberValidator implements ConstraintValidator<ValidPhone, String> {

    @Override
    public void initialize(ValidPhone phoneNumber) {
    }

    @Override
    public boolean isValid(String contactField,
                           ConstraintValidatorContext cxt) {
        if(contactField == null){
            return true;
        }
        return contactField.matches("(\\d){10}");
    }
}
