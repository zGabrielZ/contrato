package br.com.gabrielferreira.contratos.adapters.in.service;

import br.com.gabrielferreira.contratos.adapters.dto.tipotelefone.TipoTelefoneDTO;

import java.util.List;

public interface TipoTelefoneApiService {

    TipoTelefoneDTO buscarTipoTelefonePorCodigo(String codigo);

    List<TipoTelefoneDTO> buscar();
}
