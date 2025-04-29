package br.com.gabrielferreira.contratos.application.ports.in;

import br.com.gabrielferreira.contratos.application.core.model.TelefoneModel;
import br.com.gabrielferreira.contratos.application.core.model.filtro.FiltroTelefoneModel;
import br.com.gabrielferreira.contratos.application.core.model.filtro.PageInfo;

import java.util.List;
import java.util.UUID;

public interface TelefoneServiceInput {

    TelefoneModel buscarPorId(UUID id, UUID idUsuario);

    List<TelefoneModel> buscar(PageInfo pageInfo, FiltroTelefoneModel filtro, UUID idUsuario);

    void deletarPorId(UUID id, UUID idUsuario);
}
