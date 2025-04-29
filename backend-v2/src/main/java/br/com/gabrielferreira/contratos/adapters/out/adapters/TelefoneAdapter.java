package br.com.gabrielferreira.contratos.adapters.out.adapters;

import br.com.gabrielferreira.contratos.adapters.out.persistance.mapper.TelefoneEntityMapper;
import br.com.gabrielferreira.contratos.adapters.out.persistance.repository.TelefoneRepository;
import br.com.gabrielferreira.contratos.application.core.model.TelefoneModel;
import br.com.gabrielferreira.contratos.application.core.model.filtro.FiltroTelefoneModel;
import br.com.gabrielferreira.contratos.application.core.model.filtro.PageInfo;
import br.com.gabrielferreira.contratos.application.ports.out.TelefoneServiceOutput;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class TelefoneAdapter implements TelefoneServiceOutput {

    private final TelefoneRepository telefoneRepository;

    private final TelefoneEntityMapper telefoneEntityMapper;

    @Override
    public Optional<TelefoneModel> buscarPorId(UUID id, UUID idUsuario) {
        return telefoneRepository.findByIdAndUsuarioId(id, idUsuario)
                .map(telefoneEntityMapper::toModel);
    }

    @Transactional
    @Override
    public void deletarPorId(UUID id) {
        telefoneRepository.deleteById(id);
    }

    // TODO: Implementar a busca de telefones
    @Override
    public List<TelefoneModel> buscar(PageInfo pageInfo, FiltroTelefoneModel filtro, UUID idUsuario) {
        return List.of();
    }
}
