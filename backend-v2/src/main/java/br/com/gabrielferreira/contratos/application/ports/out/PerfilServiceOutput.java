package br.com.gabrielferreira.contratos.application.ports.out;

import br.com.gabrielferreira.contratos.application.core.model.PerfilModel;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PerfilServiceOutput {

    Optional<PerfilModel> buscarPorId(UUID id);

    List<PerfilModel> buscar();
}
