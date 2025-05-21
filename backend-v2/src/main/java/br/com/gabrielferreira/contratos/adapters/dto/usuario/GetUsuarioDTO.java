package br.com.gabrielferreira.contratos.adapters.dto.usuario;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.io.Serializable;
import java.util.UUID;

@Builder
public record GetUsuarioDTO(
        @Schema(
                description = "ID do usuário",
                example = "2de43b48-8f56-4741-ac33-5cf84ef1be7b"
        )
        UUID id,

        @Schema(
                description = "Nome do usuário",
                example = "João"
        )
        String nome,

        @Schema(
                description = "Sobrenome do usuário",
                example = "Silva"
        )
        String sobrenome,

        @Schema(
                description = "E-mail do usuário",
                example = "joao@email.com.br"
        )
        String email,

        @Schema(
                description = "Saldo do usuário"
        )
        SaldoDTO saldo
) implements Serializable {
}
