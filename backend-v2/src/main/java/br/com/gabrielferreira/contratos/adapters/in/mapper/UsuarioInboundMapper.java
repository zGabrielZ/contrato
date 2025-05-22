package br.com.gabrielferreira.contratos.adapters.in.mapper;

import br.com.gabrielferreira.contratos.adapters.dto.perfil.PerfilDTO;
import br.com.gabrielferreira.contratos.adapters.dto.telefone.TelefoneDTO;
import br.com.gabrielferreira.contratos.adapters.dto.tipotelefone.TipoTelefoneDTO;
import br.com.gabrielferreira.contratos.adapters.dto.usuario.GetUsuarioDTO;
import br.com.gabrielferreira.contratos.adapters.dto.usuario.UsuarioDTO;
import br.com.gabrielferreira.contratos.adapters.dto.usuario.create.CreateTelefoneDTO;
import br.com.gabrielferreira.contratos.adapters.dto.usuario.create.CreateUsuarioDTO;
import br.com.gabrielferreira.contratos.adapters.dto.usuario.filter.FilterUsuarioDTO;
import br.com.gabrielferreira.contratos.application.core.enums.TipoTelefoneEnum;
import br.com.gabrielferreira.contratos.application.core.model.PerfilModel;
import br.com.gabrielferreira.contratos.application.core.model.TelefoneModel;
import br.com.gabrielferreira.contratos.application.core.model.UsuarioModel;
import br.com.gabrielferreira.contratos.application.core.model.filtro.FiltroUsuarioModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface UsuarioInboundMapper {

    @Mapping(target = "telefones", expression = "java(mapTelefonesModel(createUsuarioDTO.telefones()))")
    UsuarioModel toModel(CreateUsuarioDTO createUsuarioDTO);

    @Mapping(target = "telefones", expression = "java(mapTelefonesDtos(usuarioModel.getTelefones()))")
    @Mapping(target = "saldo.valor", source = "usuarioModel.saldoTotal.valor")
    UsuarioDTO toDto(UsuarioModel usuarioModel);

    @Mapping(target = "saldo.valor", source = "usuarioModel.saldoTotal.valor")
    GetUsuarioDTO toGetDto(UsuarioModel usuarioModel);

    PerfilDTO toDto(PerfilModel perfilModel);

    @Mapping(target = "tipoTelefone", expression = "java(mapTipoTelefoneEnum(createTelefoneDTO.tipoTelefone()))")
    TelefoneModel toModel(CreateTelefoneDTO createTelefoneDTO);

    @Mapping(target = "tipoTelefone", expression = "java(mapTipoTelefoneDto(telefoneModel.getTipoTelefone()))")
    TelefoneDTO toDto(TelefoneModel telefoneModel);

    FiltroUsuarioModel toModel(FilterUsuarioDTO filterUsuarioDTO);

    @Named("mapTelefonesModel")
    default List<TelefoneModel> mapTelefonesModel(List<CreateTelefoneDTO> telefones) {
        if (!CollectionUtils.isEmpty(telefones)) {
            return telefones.stream()
                    .map(this::toModel)
                    .collect(Collectors.toCollection(ArrayList::new));
        }
        return new ArrayList<>();
    }

    @Named("mapTelefonesDtos")
    default List<TelefoneDTO> mapTelefonesDtos(List<TelefoneModel> telefones) {
        if (!CollectionUtils.isEmpty(telefones)) {
            return telefones.stream()
                    .map(this::toDto)
                    .collect(Collectors.toCollection(ArrayList::new));
        }
        return new ArrayList<>();
    }

    @Named("mapTipoTelefoneDto")
    default TipoTelefoneDTO mapTipoTelefoneDto(TipoTelefoneEnum tipoTelefone) {
        if (Objects.nonNull(tipoTelefone)) {
            return TipoTelefoneDTO.builder()
                    .codigo(tipoTelefone.name())
                    .descricao(tipoTelefone.getDescricao())
                    .build();
        }
        return null;
    }

    @Named("mapTipoTelefoneEnum")
    default TipoTelefoneEnum mapTipoTelefoneEnum(String tipoTelefone) {
        return TipoTelefoneEnum.buscarPorCodigo(tipoTelefone);
    }

    default List<PerfilDTO> toDtos(List<PerfilModel> perfilModels) {
        if (!CollectionUtils.isEmpty(perfilModels)) {
            return perfilModels.stream()
                    .map(this::toDto)
                    .collect(Collectors.toCollection(ArrayList::new));
        }
        return new ArrayList<>();
    }

    default List<GetUsuarioDTO> toGetUsuarioDtos(List<UsuarioModel> usuarioModels) {
        if (!CollectionUtils.isEmpty(usuarioModels)) {
            return usuarioModels.stream()
                    .map(this::toGetDto)
                    .collect(Collectors.toCollection(ArrayList::new));
        }
        return new ArrayList<>();
    }
}
