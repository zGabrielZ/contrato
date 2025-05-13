package br.com.gabrielferreira.contratos.application.core.service;

import br.com.gabrielferreira.contratos.application.core.enums.TipoTelefoneEnum;
import br.com.gabrielferreira.contratos.application.exception.NaoEncontradoException;
import br.com.gabrielferreira.contratos.application.ports.in.TipoTelefoneServiceInput;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class TipoTelefoneServiceImpl implements TipoTelefoneServiceInput {

    @Override
    public TipoTelefoneEnum buscarTipoTelefonePorCodigo(String codigo) {
        TipoTelefoneEnum tipoTelefone = TipoTelefoneEnum.buscarPorCodigo(codigo);
        if (Objects.isNull(tipoTelefone)) {
            throw new NaoEncontradoException("Tipo de telefone não encontrado");
        }
        return tipoTelefone;
    }

    @Override
    public List<TipoTelefoneEnum> buscar() {
        return Arrays.stream(TipoTelefoneEnum.values()).toList();
    }
}
