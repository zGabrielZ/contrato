package br.com.gabrielferreira.contratos.adapters.dto.usuario.filter;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Builder;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

@Builder
public record FilterUsuarioDTO(
        @Schema(
                description = "ID do usuário",
                example = "2de43b48-8f56-4741-ac33-5cf84ef1be7b"
        )
        UUID id,

        @Schema(
                description = "Nome do usuário",
                example = "João"
        )
        @Size(max = 255, min = 1)
        String nome,

        @Schema(
                description = "Sobrenome do usuário",
                example = "Silva"
        )
        @Size(max = 255, min = 1)
        String sobrenome,

        @Schema(
                description = "E-mail do usuário",
                example = "teste@email.com.br"
        )
        @Email
        String email,

        @Schema(
                description = "Saldo total início do usuário",
                example = "100.00"
        )
        @PositiveOrZero
        @Digits(integer = 10, fraction = 2)
        BigDecimal saldoTotalInicial,

        @Schema(
                description = "Saldo total final do usuário",
                example = "200.00"
        )
        @PositiveOrZero
        @Digits(integer = 10, fraction = 2)
        BigDecimal saldoTotalFinal
) implements Serializable {
}
