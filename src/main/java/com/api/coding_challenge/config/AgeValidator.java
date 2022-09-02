package com.api.coding_challenge.config;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class AgeValidator implements ConstraintValidator<ValidAge, LocalDate> {

    private ValidAge constraintAnnotation;

    public void initialize(ValidAge constraintAnnotation) {
        this.constraintAnnotation = constraintAnnotation;
    }

    public boolean isValid(LocalDate value, ConstraintValidatorContext context) {
        ZoneId defaultZoneId = ZoneId.systemDefault();
        if (value == null) {
            return true;
        } else {
            Date date = Date.from(value.atStartOfDay(defaultZoneId).toInstant());
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.YEAR, -constraintAnnotation.min());
            return !cal.getTime().before(date);
        }
    }

}