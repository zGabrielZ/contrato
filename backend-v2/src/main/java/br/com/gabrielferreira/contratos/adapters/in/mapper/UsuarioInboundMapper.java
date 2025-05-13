package br.com.gabrielferreira.contratos.adapters.in.mapper;

import br.com.gabrielferreira.contratos.adapters.dto.telefone.TelefoneDTO;
import br.com.gabrielferreira.contratos.adapters.dto.tipotelefone.TipoTelefoneDTO;
import br.com.gabrielferreira.contratos.adapters.dto.usuario.UsuarioDTO;
import br.com.gabrielferreira.contratos.adapters.dto.usuario.create.CreateTelefoneDTO;
import br.com.gabrielferreira.contratos.adapters.dto.usuario.create.CreateUsuarioDTO;
import br.com.gabrielferreira.contratos.application.core.enums.TipoTelefoneEnum;
import br.com.gabrielferreira.contratos.application.core.model.TelefoneModel;
import br.com.gabrielferreira.contratos.application.core.model.UsuarioModel;
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

    @Mapping(target = "tipoTelefone", expression = "java(mapTipoTelefoneEnum(createTelefoneDTO.tipoTelefone()))")
    TelefoneModel toModel(CreateTelefoneDTO createTelefoneDTO);

    @Mapping(target = "tipoTelefone", expression = "java(mapTipoTelefoneDto(telefoneModel.getTipoTelefone()))")
    TelefoneDTO toDto(TelefoneModel telefoneModel);

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
}
