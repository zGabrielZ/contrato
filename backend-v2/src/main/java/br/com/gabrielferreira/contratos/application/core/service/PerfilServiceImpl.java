package br.com.gabrielferreira.contratos.application.core.service;

import br.com.gabrielferreira.contratos.application.core.model.PerfilModel;
import br.com.gabrielferreira.contratos.application.exception.NaoEncontradoException;
import br.com.gabrielferreira.contratos.application.ports.in.PerfilServiceInput;
import br.com.gabrielferreira.contratos.application.ports.out.PerfilServiceOutput;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
public class PerfilServiceImpl implements PerfilServiceInput {

    private final PerfilServiceOutput perfilServiceOutput;

    @Override
    public PerfilModel buscarPorId(UUID id) {
        return perfilServiceOutput.buscarPorId(id)
                .orElseThrow(() -> new NaoEncontradoException("Perfil não encontrado"));
    }

    @Override
    public List<PerfilModel> buscar() {
        return perfilServiceOutput.buscar();
    }
}
