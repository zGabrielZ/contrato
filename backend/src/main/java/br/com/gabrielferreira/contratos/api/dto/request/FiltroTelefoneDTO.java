package br.com.gabrielferreira.contratos.api.dto.request;

import br.com.gabrielferreira.contratos.api.validator.EnumValid;
import br.com.gabrielferreira.contratos.domain.model.enums.TipoTelefoneEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.time.LocalDate;

public record FiltroTelefoneDTO(
        @Schema(description = "Id do telefone", example = "1")
        Long id,

        @Schema(description = "DDD do telefone ou celular", example = "11")
        @Size(min = 2, max = 2)
        @Pattern(regexp = "^[0-9]+$")
        String ddd,

        @Schema(description = "Número do telefone ou celular", example = "999999999")
        @Size(max = 9, min = 8)
        @Pattern(regexp = "^[0-9]+$")
        String numero,

        @Schema(description = "Descrição do telefone ou celular", example = "Descrição do telefone tal....")
        @Size(max = 255, min = 1)
        String descricao,

        @Schema(description = "Tipo do telefone", example = "RESIDENCIAL")
        @EnumValid(enumClass = TipoTelefoneEnum.class)
        String tipoTelefone,

        @Schema(description = "Criação do telefone", example = "2024-08-18")
        LocalDate dataCadastro,

        @Schema(description = "Atualização do telefone", example = "2024-08-18")
        LocalDate dataAtualizacao
) implements Serializable {
}
