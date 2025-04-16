package br.com.gabrielferreira.contratos.api.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;

public record PerfilDTO(
        @Schema(description = "Id do perfil", example = "1")
        Long id,

        @Schema(description = "Descrição do perfil", example = "Administrador")
        String descricao,

        @Schema(description = "Autoridade do perfil", example = "ROLE_ADMIN")
        String autoriedade
) implements Serializable {
}
