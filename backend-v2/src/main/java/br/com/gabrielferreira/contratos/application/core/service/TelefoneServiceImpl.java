package br.com.gabrielferreira.contratos.application.core.service;

import br.com.gabrielferreira.contratos.application.core.model.TelefoneModel;
import br.com.gabrielferreira.contratos.application.core.model.UsuarioModel;
import br.com.gabrielferreira.contratos.application.core.model.filtro.FiltroTelefoneModel;
import br.com.gabrielferreira.contratos.application.core.model.filtro.PageInfo;
import br.com.gabrielferreira.contratos.application.exception.NaoEncontradoException;
import br.com.gabrielferreira.contratos.application.ports.in.TelefoneServiceInput;
import br.com.gabrielferreira.contratos.application.ports.in.UsuarioServiceInput;
import br.com.gabrielferreira.contratos.application.ports.out.TelefoneServiceOutput;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
public class TelefoneServiceImpl implements TelefoneServiceInput {

    private final TelefoneServiceOutput telefoneServiceOutput;

    private final UsuarioServiceInput usuarioServiceInput;

    @Override
    public TelefoneModel buscarPorId(UUID id, UUID idUsuario) {
        UsuarioModel usuario = usuarioServiceInput.buscarPorId(idUsuario);
        return telefoneServiceOutput.buscarPorId(id, usuario.getId())
                .orElseThrow(() -> new NaoEncontradoException("Telefone não encontrado"));
    }

    @Override
    public List<TelefoneModel> buscar(PageInfo pageInfo, FiltroTelefoneModel filtro, UUID idUsuario) {
        usuarioServiceInput.buscarPorId(idUsuario);
        return telefoneServiceOutput.buscar(pageInfo, filtro, idUsuario);
    }

    @Override
    public void deletarPorId(UUID id, UUID idUsuario) {
        TelefoneModel telefone = buscarPorId(id, idUsuario);
        telefoneServiceOutput.deletarPorId(telefone.getId());
    }
}
