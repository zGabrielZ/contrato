package br.com.gabrielferreira.contratos.adapters.out.adapters;

import br.com.gabrielferreira.contratos.adapters.out.persistance.entity.PerfilEntity;
import br.com.gabrielferreira.contratos.adapters.out.persistance.entity.UsuarioEntity;
import br.com.gabrielferreira.contratos.adapters.out.persistance.mapper.PerfilEntityMapper;
import br.com.gabrielferreira.contratos.adapters.out.persistance.mapper.UsuarioEntityMapper;
import br.com.gabrielferreira.contratos.adapters.out.persistance.repository.PerfilRepository;
import br.com.gabrielferreira.contratos.adapters.out.persistance.repository.UsuarioRepository;
import br.com.gabrielferreira.contratos.application.core.model.PerfilModel;
import br.com.gabrielferreira.contratos.application.core.model.UsuarioModel;
import br.com.gabrielferreira.contratos.application.core.model.filtro.FiltroUsuarioModel;
import br.com.gabrielferreira.contratos.application.core.model.filtro.PageInfo;
import br.com.gabrielferreira.contratos.application.ports.out.UsuarioServiceOutput;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class UsuarioAdapter implements UsuarioServiceOutput {

    private final UsuarioRepository usuarioRepository;

    private final PerfilRepository perfilRepository;

    private final UsuarioEntityMapper usuarioEntityMapper;

    private final PerfilEntityMapper perfilEntityMapper;

    @Transactional
    @Override
    public UsuarioModel salvar(UsuarioModel usuarioModel) {
        UsuarioEntity entity = usuarioEntityMapper.toEntity(usuarioModel);
        entity = usuarioRepository.save(entity);
        return usuarioEntityMapper.toModel(entity);
    }

    @Override
    public Optional<UsuarioModel> buscarPorId(UUID id) {
        return usuarioRepository.findById(id)
                .map(usuarioEntityMapper::toModel);
    }

    @Transactional
    @Override
    public void deletarPorId(UUID id) {
        usuarioRepository.deleteById(id);
    }

    @Override
    public Optional<UsuarioModel> buscarPorEmail(String email) {
        return usuarioRepository.findByEmail(email)
                .map(usuarioEntityMapper::toModel2);
    }

    // TODO: implementar consulta de usuários com filtro
    @Override
    public List<UsuarioModel> buscar(PageInfo pageInfo, FiltroUsuarioModel filtro) {
        return List.of();
    }

    @Override
    public List<PerfilModel> buscarPerfisPorUsuario(UUID idUsuario) {
        List<PerfilEntity> perfis = perfilRepository.findAllByUsuarioId(idUsuario);
        return perfilEntityMapper.toModels(perfis);
    }
}
