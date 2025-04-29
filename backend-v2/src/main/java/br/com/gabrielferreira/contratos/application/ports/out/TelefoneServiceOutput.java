package br.com.gabrielferreira.contratos.application.ports.out;

import br.com.gabrielferreira.contratos.application.core.model.TelefoneModel;
import br.com.gabrielferreira.contratos.application.core.model.filtro.FiltroTelefoneModel;
import br.com.gabrielferreira.contratos.application.core.model.filtro.PageInfo;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TelefoneServiceOutput {

    Optional<TelefoneModel> buscarPorId(UUID id, UUID idUsuario);

    void deletarPorId(UUID id);

    List<TelefoneModel> buscar(PageInfo pageInfo, FiltroTelefoneModel filtro, UUID idUsuario);
}
