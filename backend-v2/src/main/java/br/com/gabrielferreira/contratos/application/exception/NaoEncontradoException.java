package br.com.gabrielferreira.contratos.application.exception;

import java.io.Serial;

public class NaoEncontradoException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 6963681717731248535L;

    public NaoEncontradoException(String msg) {
        super(msg);
    }
}
