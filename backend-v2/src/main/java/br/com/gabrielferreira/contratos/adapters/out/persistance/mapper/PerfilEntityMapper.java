package br.com.gabrielferreira.contratos.adapters.out.persistance.mapper;

import br.com.gabrielferreira.contratos.adapters.out.persistance.entity.PerfilEntity;
import br.com.gabrielferreira.contratos.application.core.model.PerfilModel;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PerfilEntityMapper {

    PerfilModel toModel(PerfilEntity perfilEntity);

    default List<PerfilModel> toModels(List<PerfilEntity> entities) {
        return entities.stream()
                .map(this::toModel)
                .toList();
    }
}
