package by.com.lifetech.billingapi.configurations.properties.inputchecking;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ValidMsisdnValidator.class)
public @interface MsisdnDefaultCheck {
    String message() default  "Invalid MSISDN: must be between {min} and {max} characters";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    int min() default InputParametersCheckingConfig.MSISDN_MIN_LENGTH;
    int max() default InputParametersCheckingConfig.MSISDN_MAX_LENGTH;
}