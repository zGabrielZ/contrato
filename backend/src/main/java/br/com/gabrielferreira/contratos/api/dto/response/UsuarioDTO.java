package br.com.gabrielferreira.contratos.api.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;

public record UsuarioDTO(
        @Schema(description = "Id do usuário", example = "1")
        Long id,

        @Schema(description = "Nome do usuário", example = "Gabriel")
        String nome,

        @Schema(description = "Sobrenome do usuário", example = "Ferreira")
        String sobrenome,

        @Schema(description = "E-mail do usuário", example = "test@email.com")
        String email,

        @Schema(description = "Perfis do usuário")
        List<PerfilDTO> perfis,

        @Schema(description = "Saldo total do usuário", example = "100.00")
        BigDecimal saldoTotal,

        @Schema(description = "Criação do usuário", example = "2024-08-18T15:21:37.7822381Z")
        ZonedDateTime dataCadastro,

        @Schema(description = "Atualização do usuário", example = "2024-08-18T15:21:37.7822381Z")
        ZonedDateTime dataAtualizacao
) implements Serializable {
}
