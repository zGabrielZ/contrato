package br.com.gabrielferreira.contratos.application.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum TipoTelefoneEnum {
    RESIDENCIAL("Residencial"),
    COMERCIAL("Comercial"),
    CELULAR("Celular");

    private final String descricao;

    public static boolean isResidencial(TipoTelefoneEnum tipoTelefone) {
        return tipoTelefone.equals(RESIDENCIAL);
    }

    public static boolean isComercial(TipoTelefoneEnum tipoTelefone) {
        return tipoTelefone.equals(COMERCIAL);
    }

    public static boolean isCelular(TipoTelefoneEnum tipoTelefone) {
        return tipoTelefone.equals(CELULAR);
    }
}
