package br.com.gabrielferreira.contratos.adapters.out.persistance.mapper;

import br.com.gabrielferreira.contratos.adapters.out.persistance.entity.TelefoneEntity;
import br.com.gabrielferreira.contratos.application.core.model.TelefoneModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TelefoneEntityMapper {

    TelefoneModel toModel(TelefoneEntity telefoneEntity);
}
