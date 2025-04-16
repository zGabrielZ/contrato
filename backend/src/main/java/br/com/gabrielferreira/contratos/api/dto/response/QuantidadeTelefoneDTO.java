package br.com.gabrielferreira.contratos.api.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;

public record QuantidadeTelefoneDTO(
        @Schema(description = "Quantidade de telefone por usuário", example = "2")
        Long quantidadeDeTelefone
) implements Serializable {
}
