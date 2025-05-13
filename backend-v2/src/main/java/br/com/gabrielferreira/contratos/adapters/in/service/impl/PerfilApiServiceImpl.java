package br.com.gabrielferreira.contratos.adapters.in.service.impl;

import br.com.gabrielferreira.contratos.adapters.dto.perfil.PerfilDTO;
import br.com.gabrielferreira.contratos.adapters.in.mapper.PerfilInboundMapper;
import br.com.gabrielferreira.contratos.adapters.in.service.PerfilApiService;
import br.com.gabrielferreira.contratos.application.ports.in.PerfilServiceInput;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PerfilApiServiceImpl implements PerfilApiService {

    private final PerfilServiceInput perfilServiceInput;

    private final PerfilInboundMapper mapper;

    @Override
    public PerfilDTO buscarPorId(UUID id) {
        return mapper.toDto(
                perfilServiceInput.buscarPorId(id)
        );
    }

    @Override
    public List<PerfilDTO> buscar() {
        return mapper.toDtos(
                perfilServiceInput.buscar()
        );
    }
}
