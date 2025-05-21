package br.com.gabrielferreira.contratos.adapters.dto.usuario;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.io.Serializable;
import java.math.BigDecimal;

@Builder
public record SaldoDTO(
        @Schema(
                description = "Saldo",
                example = "2500.00"
        )
        BigDecimal valor
) implements Serializable {
}
