package com.api.poke.controller.validators;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({TYPE, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = {CreatePokemonRequestValidator.class})
@Documented
public @interface ValidEvolutionLevelConstraint {

    String message() default "{org.hibernate.validator.referenceguide.chapter06.classlevel." +
            "ValidPassengerCount.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}