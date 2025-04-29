package br.com.gabrielferreira.contratos.application.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum TipoMovimentacaoEnum {
    DEPOSITO("Depósito"),
    SAQUE("Saque");

    private final String descricao;

    public static boolean isSaque(TipoMovimentacaoEnum tipoMovimentacao) {
        return SAQUE.equals(tipoMovimentacao);
    }

    public static boolean isDeposito(TipoMovimentacaoEnum tipoMovimentacao) {
        return DEPOSITO.equals(tipoMovimentacao);
    }
}
