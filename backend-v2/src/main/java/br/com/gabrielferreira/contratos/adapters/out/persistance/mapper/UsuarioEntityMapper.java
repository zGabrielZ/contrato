package br.com.gabrielferreira.contratos.adapters.out.persistance.mapper;

import br.com.gabrielferreira.contratos.adapters.out.persistance.entity.*;
import br.com.gabrielferreira.contratos.application.core.model.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface UsuarioEntityMapper {

    @Mapping(target = "telefones", expression = "java(toTelefoneEntityList(usuarioModel.getTelefones()))")
    @Mapping(target = "perfis", expression = "java(toPerfilEntityList(usuarioModel.getPerfis()))")
    UsuarioEntity toEntity(UsuarioModel usuarioModel);

    @Mapping(target = "telefones", expression = "java(toTelefoneModelList(usuarioEntity.getTelefones()))")
    @Mapping(target = "perfis", expression = "java(toPerfilModelList(usuarioEntity.getPerfis()))")
    @Mapping(target = "movimentacaoSaldos", ignore = true)
    @Mapping(target = "contratos", ignore = true)
    UsuarioModel toModelSave(UsuarioEntity usuarioEntity);

    @Mapping(target = "telefones", ignore = true)
    @Mapping(target = "movimentacaoSaldos", ignore = true)
    @Mapping(target = "contratos", ignore = true)
    @Mapping(target = "perfis", expression = "java(toPerfilModelList(usuarioEntity.getPerfis()))")
    UsuarioModel toModelRetrieve(UsuarioEntity usuarioEntity);

    @Mapping(target = "usuario.telefones", ignore = true)
    TelefoneEntity toTelefoneEntity(TelefoneModel telefoneModel);

    @Mapping(target = "usuarios", ignore = true)
    PerfilEntity toPerfilEntity(PerfilModel perfilModel);

    @Mapping(target = "usuario", ignore = true)
    TelefoneModel toTelefoneModel(TelefoneEntity telefoneEntity);

    @Mapping(target = "usuarios", ignore = true)
    PerfilModel toPerfilModel(PerfilEntity perfilEntity);

    @Named("toPerfilModelList")
    default List<PerfilModel> toPerfilModelList(List<PerfilEntity> perfilEntities) {
        if (!CollectionUtils.isEmpty(perfilEntities)) {
            return perfilEntities.stream()
                    .map(this::toPerfilModel)
                    .collect(Collectors.toCollection(ArrayList::new));
        }
        return new ArrayList<>();
    }

    @Named("toTelefoneModelList")
    default List<TelefoneModel> toTelefoneModelList(List<TelefoneEntity> telefoneEntities) {
        if (!CollectionUtils.isEmpty(telefoneEntities)) {
            return telefoneEntities.stream()
                    .map(this::toTelefoneModel)
                    .collect(Collectors.toCollection(ArrayList::new));
        }
        return new ArrayList<>();
    }

    @Named("toTelefoneEntityList")
    default List<TelefoneEntity> toTelefoneEntityList(List<TelefoneModel> telefoneModels) {
        if (!CollectionUtils.isEmpty(telefoneModels)) {
            return telefoneModels.stream()
                    .map(this::toTelefoneEntity)
                    .collect(Collectors.toCollection(ArrayList::new));
        }
        return new ArrayList<>();
    }

    @Named("toPerfilEntityList")
    default List<PerfilEntity> toPerfilEntityList(List<PerfilModel> perfilModels) {
        if (!CollectionUtils.isEmpty(perfilModels)) {
            return perfilModels.stream()
                    .map(this::toPerfilEntity)
                    .collect(Collectors.toCollection(ArrayList::new));
        }
        return new ArrayList<>();
    }
}
