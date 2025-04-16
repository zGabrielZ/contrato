package br.com.gabrielferreira.contratos.api.mapper;

import br.com.gabrielferreira.contratos.api.dto.request.CriarTelefoneDTO;
import br.com.gabrielferreira.contratos.api.dto.request.FiltroTelefoneDTO;
import br.com.gabrielferreira.contratos.api.dto.response.QuantidadeTelefoneDTO;
import br.com.gabrielferreira.contratos.api.dto.response.TelefoneDTO;
import br.com.gabrielferreira.contratos.domain.dao.filter.TelefoneFilterModel;
import br.com.gabrielferreira.contratos.domain.model.Telefone;
import br.com.gabrielferreira.contratos.domain.model.enums.TipoTelefoneEnum;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring")
public interface TelefoneMapper {

    @Mapping(target = "tipoTelefone", expression = "java(buildTipoTelefone(criarTelefoneDTO.tipoTelefone()))")
    Telefone toTelefone(CriarTelefoneDTO criarTelefoneDTO);

    @Mapping(target = "tipoTelefoneDescricao", expression = "java(buildTipoTelefoneDescricao(telefone.getTipoTelefone()))")
    TelefoneDTO toTelefoneDto(Telefone telefone);

    QuantidadeTelefoneDTO toQuantidadeTelefoneDto(Long quantidadeDeTelefone);

    TelefoneFilterModel toTelefoneFilterModel(FiltroTelefoneDTO filtroTelefoneDTO);

    default Page<TelefoneDTO> toTelefoneDtos(Page<Telefone> telefones) {
        return telefones.map(this::toTelefoneDto);
    }

    @Named("buildTipoTelefone")
    default TipoTelefoneEnum buildTipoTelefone(String tipoTelefone) {
        return TipoTelefoneEnum.valueOf(tipoTelefone);
    }

    @Named("buildTipoTelefoneDescricao")
    default String buildTipoTelefoneDescricao(TipoTelefoneEnum tipoTelefoneEnum) {
        return tipoTelefoneEnum.getDescricao();
    }
}
