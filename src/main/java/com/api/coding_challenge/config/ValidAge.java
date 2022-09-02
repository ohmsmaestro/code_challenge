package com.api.coding_challenge.config;

import java.lang.annotation.*;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ElementType.FIELD, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {AgeValidator.class})
@Documented
public @interface ValidAge {
    String message() default "Age must be 18 or above 18";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

    int min();

}