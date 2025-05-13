package br.com.gabrielferreira.contratos.adapters.in.mapper;

import br.com.gabrielferreira.contratos.adapters.dto.tipotelefone.TipoTelefoneDTO;
import br.com.gabrielferreira.contratos.application.core.enums.TipoTelefoneEnum;
import org.mapstruct.Mapper;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public interface TipoTelefoneInboundMapper {

    default TipoTelefoneDTO toDto(TipoTelefoneEnum tipoTelefone) {
        return TipoTelefoneDTO.builder()
                .codigo(tipoTelefone.name())
                .descricao(tipoTelefone.getDescricao())
                .build();
    }

    default List<TipoTelefoneDTO> toDtos(List<TipoTelefoneEnum> tiposTelefones) {
        if (CollectionUtils.isEmpty(tiposTelefones)) {
            return new ArrayList<>();
        }
        return tiposTelefones.stream()
                .map(this::toDto)
                .toList();
    }
}
