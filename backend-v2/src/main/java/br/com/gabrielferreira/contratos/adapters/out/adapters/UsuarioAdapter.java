package br.com.gabrielferreira.contratos.adapters.out.adapters;

import br.com.gabrielferreira.contratos.adapters.out.persistance.entity.PerfilEntity;
import br.com.gabrielferreira.contratos.adapters.out.persistance.entity.UsuarioEntity;
import br.com.gabrielferreira.contratos.adapters.out.persistance.mapper.PageRequestMapper;
import br.com.gabrielferreira.contratos.adapters.out.persistance.mapper.PerfilEntityMapper;
import br.com.gabrielferreira.contratos.adapters.out.persistance.mapper.UsuarioEntityMapper;
import br.com.gabrielferreira.contratos.adapters.out.persistance.repository.PerfilRepository;
import br.com.gabrielferreira.contratos.adapters.out.persistance.repository.UsuarioRepository;
import br.com.gabrielferreira.contratos.adapters.out.persistance.specification.UsuarioSpecification;
import br.com.gabrielferreira.contratos.application.core.model.PerfilModel;
import br.com.gabrielferreira.contratos.application.core.model.UsuarioModel;
import br.com.gabrielferreira.contratos.application.core.model.filtro.FiltroUsuarioModel;
import br.com.gabrielferreira.contratos.application.core.model.filtro.PageInfo;
import br.com.gabrielferreira.contratos.application.ports.out.UsuarioServiceOutput;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
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

    private final UsuarioSpecification usuarioSpecification;

    private final UsuarioEntityMapper usuarioEntityMapper;

    private final PerfilEntityMapper perfilEntityMapper;

    private final PageRequestMapper pageRequestMapper;

    @Transactional
    @Override
    public UsuarioModel salvar(UsuarioModel usuarioModel) {
        UsuarioEntity entity = usuarioEntityMapper.toEntity(usuarioModel);
        entity = usuarioRepository.save(entity);
        return usuarioEntityMapper.toModelSave(entity);
    }

    @Override
    public Optional<UsuarioModel> buscarPorId(UUID id) {
        return usuarioRepository.findUsuarioById(id)
                .map(usuarioEntityMapper::toModelRetrieve);
    }

    @Transactional
    @Override
    public void deletarPorId(UUID id) {
        usuarioRepository.deleteById(id);
    }

    @Override
    public Optional<UsuarioModel> buscarPorEmail(String email) {
        return usuarioRepository.findByEmail(email)
                .map(usuarioEntityMapper::toModelRetrieve);
    }

    @Override
    public List<UsuarioModel> buscar(PageInfo pageInfo, FiltroUsuarioModel filtro) {
        Specification<UsuarioEntity> spec = Specification.where(usuarioSpecification.whereTrue());
        if (filtro.isIdExistente()) {
            spec = spec.and(usuarioSpecification.findById(filtro.id()));
        }

        if (filtro.isNomeExistente()) {
            spec = spec.and(usuarioSpecification.findByNome(filtro.nome()));
        }

        if (filtro.isSobrenomeExistente()) {
            spec = spec.and(usuarioSpecification.findBySobrenome(filtro.sobrenome()));
        }

        if (filtro.isEmailExistente()) {
            spec = spec.and(usuarioSpecification.findByEmail(filtro.email()));
        }

        if (filtro.isSaldoTotalInicialExistente()) {
            spec = spec.and(usuarioSpecification.findBySaldoTotalInicial(filtro.saldoTotalInicial()));
        }

        if (filtro.isSaldoTotalFinalExistente()) {
            spec = spec.and(usuarioSpecification.findBySaldoTotalFinal(filtro.saldoTotalFinal()));
        }

        PageRequest pageRequest = pageRequestMapper.toPageRequest(pageInfo);
        return usuarioEntityMapper.toModelList(
                usuarioRepository.findAll(spec, pageRequest)
        );
    }

    @Override
    public List<PerfilModel> buscarPerfisPorUsuario(UUID idUsuario) {
        List<PerfilEntity> perfis = perfilRepository.findAllByUsuarioId(idUsuario);
        return perfilEntityMapper.toModels(perfis);
    }
}
