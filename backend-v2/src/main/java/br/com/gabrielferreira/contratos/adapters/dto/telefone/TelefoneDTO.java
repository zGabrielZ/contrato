package br.com.gabrielferreira.contratos.adapters.dto.telefone;

import br.com.gabrielferreira.contratos.adapters.dto.tipotelefone.TipoTelefoneDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.io.Serializable;
import java.util.UUID;

@Builder
public record TelefoneDTO(
        @Schema(
                description = "ID do telefone",
                example = "54842462-7200-4e9c-9a7b-6b75868556fe"
        )
        UUID id,

        @Schema(
                description = "DDD do telefone",
                example = "11"
        )
        String ddd,

        @Schema(
                description = "Número do telefone",
                example = "999999999"
        )
        String numero,

        @Schema(
                description = "Descrição do telefone",
                example = "Telefone de casa"
        )
        String descricao,

        @Schema(
                description = "Tipo do telefone"
        )
        TipoTelefoneDTO tipoTelefone
) implements Serializable {
}
