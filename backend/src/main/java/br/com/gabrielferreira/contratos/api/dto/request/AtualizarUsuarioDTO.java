package br.com.gabrielferreira.contratos.api.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

public record AtualizarUsuarioDTO(
        @Schema(description = "Nome do usuário", example = "Gabriel")
        @NotBlank
        @Size(max = 255, min = 1)
        String nome,

        @Schema(description = "Sobrenome do usuário", example = "Ferreira")
        @NotBlank
        @Size(max = 255, min = 1)
        String sobrenome
) implements Serializable {
}
