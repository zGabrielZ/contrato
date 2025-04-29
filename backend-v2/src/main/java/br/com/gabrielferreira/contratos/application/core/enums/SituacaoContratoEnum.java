package br.com.gabrielferreira.contratos.application.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum SituacaoContratoEnum {
    INICIADO("Iniciado"),
    CONCLUIDO("Concluído");

    private final String descricao;

    public static boolean isIniciado(SituacaoContratoEnum situacaoContrato) {
        return INICIADO.equals(situacaoContrato);
    }

    public static boolean isConcluido(SituacaoContratoEnum situacaoContrato) {
        return CONCLUIDO.equals(situacaoContrato);
    }
}
