package br.com.gabrielferreira.contratos.api.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

public record PerfilIdDTO(
        @Schema(description = "Id do perfil", example = "1")
        @NotNull
        Long id
) implements Serializable {
}
