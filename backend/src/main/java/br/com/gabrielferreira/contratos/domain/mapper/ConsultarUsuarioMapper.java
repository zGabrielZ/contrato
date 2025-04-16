package br.com.gabrielferreira.contratos.domain.mapper;

import br.com.gabrielferreira.contratos.domain.dao.projection.UsuarioProjection;
import br.com.gabrielferreira.contratos.domain.model.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ConsultarUsuarioMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "nome", target = "nome")
    @Mapping(source = "sobrenome", target = "sobrenome")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "saldoTotal", target = "saldoTotal.valor")
    @Mapping(source = "dataCadastro", target = "dataCadastro")
    @Mapping(source = "dataAtualizacao", target = "dataAtualizacao")
    Usuario toUsuario(UsuarioProjection usuarioProjection);

    default List<Usuario> toUsuarios(List<UsuarioProjection> usuarioProjections) {
        return usuarioProjections.stream().map(this::toUsuario).toList();
    }
}
