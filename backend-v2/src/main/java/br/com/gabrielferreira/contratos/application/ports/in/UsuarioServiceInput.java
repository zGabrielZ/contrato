package br.com.gabrielferreira.contratos.application.ports.in;

import br.com.gabrielferreira.contratos.application.core.model.PerfilModel;
import br.com.gabrielferreira.contratos.application.core.model.filtro.FiltroUsuarioModel;
import br.com.gabrielferreira.contratos.application.core.model.filtro.PageInfo;
import br.com.gabrielferreira.contratos.application.core.model.UsuarioModel;

import java.util.List;
import java.util.UUID;

public interface UsuarioServiceInput {

    UsuarioModel cadastrar(UsuarioModel usuarioModel);

    UsuarioModel buscarPorId(UUID id);

    UsuarioModel atualizar(UUID id, UsuarioModel usuarioModel);

    void deletarPorId(UUID id);

    List<UsuarioModel> buscar(PageInfo pageInfo, FiltroUsuarioModel filtro);

    List<PerfilModel> buscarPerfisPorUsuario(UUID idUsuario);
}
