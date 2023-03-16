package com.example.mockreskill.validator;


import com.example.mockreskill.common.contants.MessageCode;
import com.example.mockreskill.common.exeption.BadRequestException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

@Slf4j
@Component
public class RequestValidator<T> {
    private Validator validator;

    public RequestValidator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        this.validator = factory.getValidator();
    }

    public void validate(T request) {
        Set<ConstraintViolation<T>> violations = validator.validate(request);
        for (ConstraintViolation<T> violation : violations) {
            if (ObjectUtils.isEmpty(violation.getInvalidValue())) {
                log.error("Missing input require: {} {}", violation.getPropertyPath().toString(), violation.getMessage());
                throw new BadRequestException(violation.getPropertyPath().toString(), MessageCode.INPUT_REQUIRED);
            } else {
                log.error("Invalid input: {} {}", violation.getPropertyPath().toString(), violation.getMessage());
                throw new BadRequestException(violation.getPropertyPath().toString(), MessageCode.INPUT_INVALID);
            }
        }
    }
}
