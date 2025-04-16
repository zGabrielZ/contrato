package br.com.gabrielferreira.contratos.api.mapper;

import br.com.gabrielferreira.contratos.api.dto.response.PerfilDTO;
import br.com.gabrielferreira.contratos.domain.model.Perfil;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PerfilMapper {

    PerfilDTO toPerfilDto(Perfil perfil);

    default List<PerfilDTO> toPerfilDtos(List<Perfil> perfis) {
        return perfis.stream().map(this::toPerfilDto).toList();
    }
}
