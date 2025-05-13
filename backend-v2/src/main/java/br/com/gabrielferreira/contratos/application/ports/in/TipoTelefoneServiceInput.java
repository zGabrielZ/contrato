package br.com.gabrielferreira.contratos.application.ports.in;

import br.com.gabrielferreira.contratos.application.core.enums.TipoTelefoneEnum;

import java.util.List;

public interface TipoTelefoneServiceInput {

    TipoTelefoneEnum buscarTipoTelefonePorCodigo(String codigo);

    List<TipoTelefoneEnum> buscar();
}
