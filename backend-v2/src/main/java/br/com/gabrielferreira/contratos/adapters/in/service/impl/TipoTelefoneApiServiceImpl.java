package br.com.gabrielferreira.contratos.adapters.in.service.impl;

import br.com.gabrielferreira.contratos.adapters.dto.tipotelefone.TipoTelefoneDTO;
import br.com.gabrielferreira.contratos.adapters.in.mapper.TipoTelefoneInboundMapper;
import br.com.gabrielferreira.contratos.adapters.in.service.TipoTelefoneApiService;
import br.com.gabrielferreira.contratos.application.ports.in.TipoTelefoneServiceInput;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TipoTelefoneApiServiceImpl implements TipoTelefoneApiService {

    private final TipoTelefoneServiceInput tipoTelefoneServiceInput;

    private final TipoTelefoneInboundMapper mapper;

    @Override
    public TipoTelefoneDTO buscarTipoTelefonePorCodigo(String codigo) {
        return mapper.toDto(
                tipoTelefoneServiceInput.buscarTipoTelefonePorCodigo(codigo)
        );
    }

    @Override
    public List<TipoTelefoneDTO> buscar() {
        return mapper.toDtos(
                tipoTelefoneServiceInput.buscar()
        );
    }
}
