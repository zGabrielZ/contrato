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

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface UsuarioInboundMapper {

    @Mapping(target = "telefones", expression = "java(mapTelefonesModel(createUsuarioDTO.telefones()))")
    UsuarioModel toModel(CreateUsuarioDTO createUsuarioDTO);

    @Mapping(target = "telefones", expression = "java(mapTelefonesDtos(usuarioModel.getTelefones()))")
    UsuarioDTO toDto(UsuarioModel usuarioModel);

    @Named("mapTelefonesModel")
    default List<TelefoneModel> mapTelefonesModel(List<CreateTelefoneDTO> telefones) {
        return telefones.stream()
                .map(telefone -> TelefoneModel.builder()
                        .ddd(telefone.ddd())
                        .numero(telefone.numero())
                        .descricao(telefone.descricao())
                        .tipoTelefone(TipoTelefoneEnum.buscarPorCodigo(telefone.tipoTelefone()))
                        .build())
                .collect(Collectors.toList());
    }

    @Named("mapTelefonesDtos")
    default List<TelefoneDTO> mapTelefonesDtos(List<TelefoneModel> telefones) {
        return telefones.stream()
                .map(telefone -> TelefoneDTO.builder()
                        .id(telefone.getId())
                        .ddd(telefone.getDdd())
                        .numero(telefone.getNumero())
                        .descricao(telefone.getDescricao())
                        .tipoTelefone(
                                TipoTelefoneDTO.builder()
                                        .descricao(telefone.getTipoTelefone().getDescricao())
                                        .codigo(telefone.getTipoTelefone().name())
                                        .build()
                        )
                        .build())
                .toList();
    }
}
