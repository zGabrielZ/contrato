package br.com.gabrielferreira.contratos.adapters.out.adapters;

import br.com.gabrielferreira.contratos.adapters.out.persistance.entity.PerfilEntity;
import br.com.gabrielferreira.contratos.adapters.out.persistance.mapper.PerfilEntityMapper;
import br.com.gabrielferreira.contratos.adapters.out.persistance.repository.PerfilRepository;
import br.com.gabrielferreira.contratos.application.core.model.PerfilModel;
import br.com.gabrielferreira.contratos.application.ports.out.PerfilServiceOutput;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class PerfilAdapter implements PerfilServiceOutput {

    private final PerfilRepository perfilRepository;

    private final PerfilEntityMapper perfilEntityMapper;

    @Override
    public Optional<PerfilModel> buscarPorId(UUID id) {
        return perfilRepository.findById(id)
                .map(perfilEntityMapper::toModel);
    }

    @Override
    public List<PerfilModel> buscar() {
        List<PerfilEntity> perfis =  perfilRepository.findAllOrderByDescricaoAsc();
        return perfilEntityMapper.toModels(perfis);
    }
}
