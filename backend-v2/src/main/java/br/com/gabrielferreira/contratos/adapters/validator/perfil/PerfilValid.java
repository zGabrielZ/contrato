package br.com.gabrielferreira.contratos.adapters.validator.perfil;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = PerfilValidator.class)
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface PerfilValid {

    String message() default "Perfil inválido";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
