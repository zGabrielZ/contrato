package br.com.gabrielferreira.contratos.api.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

import java.io.Serializable;
import java.util.List;

public record CriarUsuarioDTO(
        @Schema(description = "Nome do usuário", example = "Gabriel")
        @NotBlank
        @Size(max = 255, min = 1)
        String nome,

        @Schema(description = "Sobrenome do usuário", example = "Ferreira")
        @NotBlank
        @Size(max = 255, min = 1)
        String sobrenome,

        @Schema(description = "E-mail do usuário", example = "test@email.com")
        @NotBlank
        @Email
        String email,

        @Schema(description = "Perfis do usuário")
        @Valid
        @NotEmpty
        @NotNull
        List<PerfilIdDTO> perfis
) implements Serializable {
}
