package br.com.gabrielferreira.contratos.adapters.validator.senha;

import br.com.gabrielferreira.contratos.common.utils.CaracteresUtils;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.apache.commons.lang3.StringUtils;

public class SenhaValidator implements ConstraintValidator<SenhaValid, String> {

    @Override
    public boolean isValid(String senha, ConstraintValidatorContext context) {
        if (StringUtils.isBlank(senha)) {
            return true;
        }

        context.disableDefaultConstraintViolation();

        if (!CaracteresUtils.isPossuiCaracteresEspecias(senha)) {
            context.buildConstraintViolationWithTemplate("A senha informada tem que ter pelo menos uma caractere especial")
                    .addConstraintViolation();
            return false;
        }

        if (!CaracteresUtils.isPossuiCaractereMaiusculas(senha)) {
            context.buildConstraintViolationWithTemplate("A senha informada tem que ter pelo menos uma caractere maiúsculas")
                    .addConstraintViolation();
            return false;
        }

        if (!CaracteresUtils.isPossuiCaractereMinusculas(senha)) {
            context.buildConstraintViolationWithTemplate("A senha informada tem que ter pelo menos uma caractere minúsculas")
                    .addConstraintViolation();
            return false;
        }

        if (!CaracteresUtils.isPossuiCaractereDigito(senha)) {
            context.buildConstraintViolationWithTemplate("A senha informada tem que ter pelo menos um caractere dígito")
                    .addConstraintViolation();
            return false;
        }

        return true;
    }
}
