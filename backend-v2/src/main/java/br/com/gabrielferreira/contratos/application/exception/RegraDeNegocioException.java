package br.com.gabrielferreira.contratos.application.exception;

import java.io.Serial;

public class RegraDeNegocioException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = -7710626527452918514L;

    public RegraDeNegocioException(String mensagem) {
        super(mensagem);
    }

    public static void throwException(boolean condicao, String mensagem) {
        if (condicao) {
            throw new RegraDeNegocioException(mensagem);
        }
    }
}
