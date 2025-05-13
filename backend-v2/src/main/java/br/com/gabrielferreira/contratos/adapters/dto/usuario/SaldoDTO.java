package br.com.gabrielferreira.contratos.adapters.dto.usuario;

import lombok.Builder;

import java.io.Serializable;
import java.math.BigDecimal;

@Builder
public record SaldoDTO(
        BigDecimal valor
) implements Serializable {
}
