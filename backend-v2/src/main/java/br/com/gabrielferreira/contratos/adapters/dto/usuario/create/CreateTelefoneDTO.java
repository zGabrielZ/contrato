package br.com.gabrielferreira.contratos.adapters.dto.usuario.create;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;

import java.io.Serializable;

@Builder
public record CreateTelefoneDTO(
        @NotBlank
        @Size(min = 2, max = 2)
        @Pattern(regexp = "^[0-9]+$")
        @Schema(
                description = "DDD do telefone",
                example = "11"
        )
        String ddd,

        @NotBlank
        @Size(max = 9, min = 8)
        @Pattern(regexp = "^[0-9]+$")
        @Schema(
                description = "Número do telefone",
                example = "999999999"
        )
        String numero,

        @Size(max = 255, min = 1)
        @Schema(
                description = "Descrição do telefone",
                example = "Telefone de casa"
        )
        String descricao,

        @NotBlank
        @Schema(
                description = "Tipo do telefone",
                example = "CELULAR"
        )
        String tipoTelefone
) implements Serializable {
}
