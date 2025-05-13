package br.com.gabrielferreira.contratos.adapters.in.mapper;

import br.com.gabrielferreira.contratos.adapters.dto.perfil.PerfilDTO;
import br.com.gabrielferreira.contratos.application.core.model.PerfilModel;
import org.mapstruct.Mapper;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public interface PerfilInboundMapper {

    PerfilDTO toDto(PerfilModel perfilModel);

    default List<PerfilDTO> toDtos(List<PerfilModel> perfilModels) {
        if (CollectionUtils.isEmpty(perfilModels)) {
            return new ArrayList<>();
        }
        return perfilModels.stream()
                .map(this::toDto)
                .toList();
    }
}
