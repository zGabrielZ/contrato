package br.com.gabrielferreira.contratos.adapters.out.persistance.mapper;

import br.com.gabrielferreira.contratos.adapters.out.persistance.entity.PerfilEntity;
import br.com.gabrielferreira.contratos.adapters.out.persistance.entity.TelefoneEntity;
import br.com.gabrielferreira.contratos.adapters.out.persistance.entity.UsuarioEntity;
import br.com.gabrielferreira.contratos.application.core.model.PerfilModel;
import br.com.gabrielferreira.contratos.application.core.model.TelefoneModel;
import br.com.gabrielferreira.contratos.application.core.model.UsuarioModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface UsuarioEntityMapper {

    @Mapping(target = "telefones", expression = "java(toTelefoneEntityList(usuarioModel.getTelefones()))")
    UsuarioEntity toEntity(UsuarioModel usuarioModel);

    @Mapping(target = "telefones", expression = "java(toTelefoneModelList(usuarioEntity.getTelefones()))")
    @Mapping(target = "perfis", expression = "java(toPerfilModelList(usuarioEntity.getPerfis()))")
    @Mapping(target = "telefones", ignore = true)
    UsuarioModel toModel(UsuarioEntity usuarioEntity);

    @Mapping(target = "saldoTotal", ignore = true)
    @Mapping(target = "telefones", expression = "java(null)")
    @Mapping(target = "perfis", expression = "java(null)")
    @Mapping(target = "contratos", expression = "java(null)")
    @Mapping(target = "movimentacaoSaldos", expression = "java(null)")
    UsuarioModel toModel2(UsuarioEntity usuarioEntity);

    @Mapping(target = "usuario.telefones", ignore = true)
    TelefoneEntity toTelefoneEntity(TelefoneModel telefoneModel);

    @Mapping(target = "usuario", ignore = true)
    TelefoneModel toTelefoneModel(TelefoneEntity telefoneEntity);

    @Mapping(target = "usuarios", ignore = true)
    PerfilModel toPerfilModel(PerfilEntity perfilEntity);

    default List<PerfilModel> toPerfilModelList(List<PerfilEntity> perfilEntities) {
        return perfilEntities.stream()
                .map(this::toPerfilModel)
                .collect(Collectors.toList());
    }

    default List<TelefoneModel> toTelefoneModelList(List<TelefoneEntity> telefoneEntities) {
        return telefoneEntities.stream()
                .map(this::toTelefoneModel)
                .collect(Collectors.toList());
    }

    default List<TelefoneEntity> toTelefoneEntityList(List<TelefoneModel> telefoneModels) {
        return telefoneModels.stream()
                .map(this::toTelefoneEntity)
                .toList();
    }
}
