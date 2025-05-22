package br.com.gabrielferreira.contratos.adapters.in.service;

import br.com.gabrielferreira.contratos.adapters.dto.perfil.PerfilDTO;
import br.com.gabrielferreira.contratos.adapters.dto.usuario.GetUsuarioDTO;
import br.com.gabrielferreira.contratos.adapters.dto.usuario.UsuarioDTO;
import br.com.gabrielferreira.contratos.adapters.dto.usuario.create.CreateUsuarioDTO;
import br.com.gabrielferreira.contratos.adapters.dto.usuario.filter.FilterUsuarioDTO;
import br.com.gabrielferreira.contratos.adapters.dto.usuario.update.UpdateUsuarioDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface UsuarioApiService {

    UsuarioDTO cadastrar(CreateUsuarioDTO create);

    GetUsuarioDTO buscarPorId(UUID id);

    UsuarioDTO atualizar(UUID id, UpdateUsuarioDTO update);

    void deletar(UUID id);

    List<GetUsuarioDTO> buscar(Pageable pageable, FilterUsuarioDTO filtro);

    List<PerfilDTO> buscarPerfis(UUID idUsuario);
}
