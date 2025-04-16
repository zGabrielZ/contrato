package br.com.gabrielferreira.contratos.domain.mapper;

import br.com.gabrielferreira.contratos.domain.dao.projection.TelefoneProjection;
import br.com.gabrielferreira.contratos.domain.model.Telefone;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ConsultarTelefoneMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "ddd", target = "ddd")
    @Mapping(source = "numero", target = "numero")
    @Mapping(source = "descricao", target = "descricao")
    @Mapping(source = "tipoTelefone", target = "tipoTelefone")
    @Mapping(source = "dataCadastro", target = "dataCadastro")
    @Mapping(source = "dataAtualizacao", target = "dataAtualizacao")
    Telefone toTelefone(TelefoneProjection telefoneProjection);

    default List<Telefone> toTelefones(List<TelefoneProjection> telefoneProjections) {
        return telefoneProjections.stream().map(this::toTelefone).toList();
    }
}
