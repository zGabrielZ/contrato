package br.com.gabrielferreira.contratos.api.dto.request;

import br.com.gabrielferreira.contratos.api.validator.EnumValid;
import br.com.gabrielferreira.contratos.domain.model.enums.TipoTelefoneEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

public record CriarTelefoneDTO(
        @Schema(description = "DDD do telefone ou celular", example = "11")
        @NotBlank
        @Size(min = 2, max = 2)
        @Pattern(regexp = "^[0-9]+$")
        String ddd,

        @Schema(description = "Número do telefone ou celular", example = "999999999")
        @NotBlank
        @Size(max = 9, min = 8)
        @Pattern(regexp = "^[0-9]+$")
        String numero,

        @Schema(description = "Descrição do telefone ou celular", example = "Descrição do telefone tal....")
        @Size(max = 255, min = 1)
        String descricao,

        @Schema(description = "Tipo do telefone", example = "RESIDENCIAL")
        @NotNull
        @EnumValid(enumClass = TipoTelefoneEnum.class)
        String tipoTelefone
) implements Serializable {
}
