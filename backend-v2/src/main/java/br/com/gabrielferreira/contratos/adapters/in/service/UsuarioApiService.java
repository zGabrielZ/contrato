package br.com.gabrielferreira.contratos.adapters.in.service;

import br.com.gabrielferreira.contratos.adapters.dto.perfil.PerfilDTO;
import br.com.gabrielferreira.contratos.adapters.dto.usuario.UsuarioDTO;
import br.com.gabrielferreira.contratos.adapters.dto.usuario.create.CreateUsuarioDTO;
import br.com.gabrielferreira.contratos.adapters.dto.usuario.update.UpdateUsuarioDTO;

import java.util.List;
import java.util.UUID;

public interface UsuarioApiService {

    UsuarioDTO cadastrar(CreateUsuarioDTO create);

    UsuarioDTO buscarPorId(UUID id);

    UsuarioDTO atualizar(UUID id, UpdateUsuarioDTO update);

    void deletar(UUID id);

    // TODO: implementar o método de busca com filtro, com paginação e etc

    List<PerfilDTO> buscarPerfis(UUID idUsuario);
}
