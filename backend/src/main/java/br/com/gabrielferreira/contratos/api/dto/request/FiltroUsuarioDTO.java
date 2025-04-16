package br.com.gabrielferreira.contratos.api.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

public record FiltroUsuarioDTO(
        @Schema(description = "Id do usuário", example = "1")
        Long id,

        @Schema(description = "Nome do usuário", example = "Gabriel")
        @Size(max = 255, min = 1)
        String nome,

        @Schema(description = "Sobrenome do usuário", example = "Ferreira")
        @Size(max = 255, min = 1)
        String sobrenome,

        @Schema(description = "E-mail do usuário", example = "teste@email.com")
        @Email
        String email,

        @Schema(description = "Saldo total início do usuário", example = "100.00")
        @PositiveOrZero
        @Digits(integer = 10, fraction = 2)
        BigDecimal saldoTotalInicial,

        @Schema(description = "Saldo total final do usuário", example = "200.00")
        @PositiveOrZero
        @Digits(integer = 10, fraction = 2)
        BigDecimal saldoTotalFinal,

        @Schema(description = "Data do cadastro do usuário", example = "2024-08-18")
        LocalDate dataCadastro,

        @Schema(description = "Data da atualização do usuário", example = "2024-08-18")
        LocalDate dataAtualizacao
) implements Serializable {
}
