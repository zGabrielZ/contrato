package br.com.gabrielferreira.contratos.adapters.in.service.impl;

import br.com.gabrielferreira.contratos.adapters.dto.perfil.PerfilDTO;
import br.com.gabrielferreira.contratos.adapters.dto.usuario.UsuarioDTO;
import br.com.gabrielferreira.contratos.adapters.dto.usuario.create.CreateUsuarioDTO;
import br.com.gabrielferreira.contratos.adapters.dto.usuario.update.UpdateUsuarioDTO;
import br.com.gabrielferreira.contratos.adapters.in.mapper.UsuarioInboundMapper;
import br.com.gabrielferreira.contratos.adapters.in.service.UsuarioApiService;
import br.com.gabrielferreira.contratos.application.core.model.UsuarioModel;
import br.com.gabrielferreira.contratos.application.ports.in.UsuarioServiceInput;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UsuarioApiServiceImpl implements UsuarioApiService {

    private final UsuarioServiceInput usuarioServiceInput;

    private final UsuarioInboundMapper mapper;

    @Override
    public UsuarioDTO cadastrar(CreateUsuarioDTO create) {
        UsuarioModel usuario = usuarioServiceInput.cadastrar(
                mapper.toModel(create)
        );
        return mapper.toDto(usuario);
    }

    @Override
    public UsuarioDTO buscarPorId(UUID id) {
        return null;
    }

    @Override
    public UsuarioDTO atualizar(UUID id, UpdateUsuarioDTO update) {
        return null;
    }

    @Override
    public void deletar(UUID id) {
        usuarioServiceInput.buscarPorId(id);
    }

    @Override
    public List<PerfilDTO> buscarPerfis(UUID idUsuario) {
        return List.of();
    }
}
