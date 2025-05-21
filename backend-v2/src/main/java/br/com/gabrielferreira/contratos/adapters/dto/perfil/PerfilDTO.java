package br.com.gabrielferreira.contratos.adapters.dto.perfil;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.io.Serializable;

@Builder
public record PerfilDTO(
        @Schema(
                description = "ID do perfil",
                example = "8124e840-f959-4ade-9e85-4e01668b8a7f"
        )
        String id,

        @Schema(
                description = "Descrição do perfil",
                example = "Administrador"
        )
        String descricao,

        @Schema(
                description = "Autoriedade do perfil",
                example = "ROLE_ADMIN"
        )
        String autoriedade
) implements Serializable {
}
