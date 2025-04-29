package br.com.gabrielferreira.contratos.adapters.out.persistance.mapper;

import br.com.gabrielferreira.contratos.adapters.out.persistance.entity.UsuarioEntity;
import br.com.gabrielferreira.contratos.application.core.model.UsuarioModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsuarioEntityMapper {

    UsuarioEntity toEntity(UsuarioModel usuarioModel);

    UsuarioModel toModel(UsuarioEntity usuarioEntity);
}
