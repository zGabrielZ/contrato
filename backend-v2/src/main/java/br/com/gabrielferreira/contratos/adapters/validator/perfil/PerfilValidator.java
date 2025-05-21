package br.com.gabrielferreira.contratos.adapters.validator.perfil;

import br.com.gabrielferreira.contratos.adapters.dto.usuario.IdPerfilDTO;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class PerfilValidator implements ConstraintValidator<PerfilValid, List<IdPerfilDTO>> {

    @Override
    public boolean isValid(List<IdPerfilDTO> idPerfilDTOS, ConstraintValidatorContext context) {
        if (CollectionUtils.isEmpty(idPerfilDTOS)) {
            return true;
        }

        context.disableDefaultConstraintViolation();

        boolean valido = true;
        List<UUID> idsPerfis = idPerfilDTOS.stream()
                .map(IdPerfilDTO::id)
                .toList();

        for (UUID idPerfil : idsPerfis) {
            int duplicados = Collections.frequency(idsPerfis, idPerfil);
            if (duplicados > 1) {
                context.buildConstraintViolationWithTemplate("Não é permitido perfis duplicados")
                        .addConstraintViolation();
                valido = false;
                break;
            }
        }

        return valido;
    }
}
