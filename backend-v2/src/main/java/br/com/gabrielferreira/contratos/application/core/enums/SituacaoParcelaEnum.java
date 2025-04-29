package br.com.gabrielferreira.contratos.application.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum SituacaoParcelaEnum {
    EM_ANDAMENTO("Em andamento"),
    PAGO("Pago"),
    ATRASADO("Atrasado");

    private final String descricao;

    public static boolean isAndamento(SituacaoParcelaEnum situacaoParcela) {
        return EM_ANDAMENTO.equals(situacaoParcela);
    }

    public static boolean isPago(SituacaoParcelaEnum situacaoParcela) {
        return PAGO.equals(situacaoParcela);
    }

    public static boolean isAtrasado(SituacaoParcelaEnum situacaoParcela) {
        return ATRASADO.equals(situacaoParcela);
    }
}
