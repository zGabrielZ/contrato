package br.com.gabrielferreira.contratos.application.ports.out;

import br.com.gabrielferreira.contratos.application.core.model.PerfilModel;
import br.com.gabrielferreira.contratos.application.core.model.UsuarioModel;
import br.com.gabrielferreira.contratos.application.core.model.filtro.FiltroUsuarioModel;
import br.com.gabrielferreira.contratos.application.core.model.filtro.PageInfo;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UsuarioServiceOutput {

    UsuarioModel salvar(UsuarioModel usuarioModel);

    Optional<UsuarioModel> buscarPorId(UUID id);

    void deletarPorId(UUID id);

    Optional<UsuarioModel> buscarPorEmail(String email);

    List<UsuarioModel> buscar(PageInfo pageInfo, FiltroUsuarioModel filtro);

    List<PerfilModel> buscarPerfisPorUsuario(UUID idUsuario);
}
