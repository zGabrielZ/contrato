package br.com.gabrielferreira.contratos.adapters.dto.usuario;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.io.Serializable;
import java.util.UUID;

@Builder
public record IdPerfilDTO(
        @Schema(
                description = "ID do perfil",
                example = "0099da34-8fc6-4613-80a9-8dbe21825c6d"
        )
        @NotNull
        UUID id
) implements Serializable {
}
