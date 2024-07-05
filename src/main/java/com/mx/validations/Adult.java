package com.mx.validations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {AdultValidator.class})
@Documented
public @interface Adult {

	String message() default "El Empleado debe ser mayor de edad.";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}