package br.com.gabrielferreira.contratos.domain.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum SituacaoContratoEnum {
    INICIADO("Iniciado"),
    CONCLUIDO("Concluído");

    private final String descricao;
}
