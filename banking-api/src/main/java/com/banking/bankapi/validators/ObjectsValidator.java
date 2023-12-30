package com.banking.bankapi.validators;

import com.banking.bankapi.exceptions.ObjectValidationException;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class ObjectsValidator<T> {

    private final ValidatorFactory factor = Validation.buildDefaultValidatorFactory();
    private final Validator validator = factor.getValidator();
    public void validate(T object) {
       Set<ConstraintViolation<T>> violations = validator.validate(object);

        if (!violations.isEmpty()) {
            Set<String> errorMessages = violations.stream()
                    .map(violation -> violation.getMessage())
                    .collect(Collectors.toSet());
            //raise exception
            throw new ObjectValidationException(errorMessages, object.getClass().getName());
        }
    }
}
