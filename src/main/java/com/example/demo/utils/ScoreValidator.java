package com.example.demo.utils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import java.lang.annotation.Annotation;

public class ScoreValidator implements ConstraintValidator<ValidScore,Double> {

    @Override
    public void initialize(ValidScore constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Double score, ConstraintValidatorContext constraintValidatorContext) {
        if(score >=0 && score<=100)
            return true;
        return false;
    }
}
