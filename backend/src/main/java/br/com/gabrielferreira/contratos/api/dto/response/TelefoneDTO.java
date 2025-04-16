package br.com.gabrielferreira.contratos.api.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;
import java.time.ZonedDateTime;

public record TelefoneDTO(
        @Schema(description = "Id do telefone", example = "1")
        Long id,

        @Schema(description = "DDD do telefone ou celular", example = "11")
        String ddd,

        @Schema(description = "Número do telefone ou celular", example = "999999999")
        String numero,

        @Schema(description = "Descrição do telefone ou celular", example = "Descrição do telefone tal....")
        String descricao,

        @Schema(description = "Tipo do telefone", example = "RESIDENCIAL")
        String tipoTelefone,

        @Schema(description = "Descrição do tipo telefone", example = "Residencial")
        String tipoTelefoneDescricao,

        @Schema(description = "Criação do telefone", example = "2024-08-18T15:21:37.7822381Z")
        ZonedDateTime dataCadastro,

        @Schema(description = "Atualização do telefone", example = "2024-08-18T15:21:37.7822381Z")
        ZonedDateTime dataAtualizacao
) implements Serializable {
}
