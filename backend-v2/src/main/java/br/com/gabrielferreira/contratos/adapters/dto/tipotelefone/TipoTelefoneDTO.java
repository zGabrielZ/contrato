package br.com.gabrielferreira.contratos.adapters.dto.tipotelefone;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.io.Serializable;

@Builder
public record TipoTelefoneDTO(
        @Schema(
                description = "Descrição do tipo de telefone",
                example = "Celular"
        )
        String descricao,

        @Schema(
                description = "Código do tipo de telefone",
                example = "CELULAR"
        )
        String codigo
) implements Serializable {
}
