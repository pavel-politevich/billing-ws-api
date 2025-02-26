package by.com.lifetech.billingapi.configurations.properties.inputchecking;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValidMsisdnValidator implements ConstraintValidator<MsisdnDefaultCheck, String> {
    private int min;
    private int max;

    @Override
    public void initialize(MsisdnDefaultCheck constraintAnnotation) {
        this.min = constraintAnnotation.min();
        this.max = constraintAnnotation.max();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value != null && value.length() >= min && value.length() <= max;
    }
}
