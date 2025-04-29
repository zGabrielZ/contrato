package br.com.gabrielferreira.contratos.application.ports.in;

import br.com.gabrielferreira.contratos.application.core.model.PerfilModel;

import java.util.List;
import java.util.UUID;

public interface PerfilServiceInput {

    PerfilModel buscarPorId(UUID id);

    List<PerfilModel> buscar();
}
