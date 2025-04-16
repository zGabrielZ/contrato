package br.com.gabrielferreira.contratos.api.mapper;

import br.com.gabrielferreira.contratos.api.dto.request.AtualizarUsuarioDTO;
import br.com.gabrielferreira.contratos.api.dto.request.CriarUsuarioDTO;
import br.com.gabrielferreira.contratos.api.dto.request.FiltroUsuarioDTO;
import br.com.gabrielferreira.contratos.api.dto.response.UsuarioDTO;
import br.com.gabrielferreira.contratos.api.dto.response.UsuarioResumidoDTO;
import br.com.gabrielferreira.contratos.domain.model.Usuario;
import br.com.gabrielferreira.contratos.domain.dao.filter.UsuarioFilterModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    Usuario toUsuario(CriarUsuarioDTO criarUsuarioDTO);

    Usuario toUsuario(AtualizarUsuarioDTO atualizarUsuarioDTO);

    @Mapping(source = "usuario.saldoTotal.valor", target = "saldoTotal")
    UsuarioDTO toUsuarioDto(Usuario usuario);

    UsuarioFilterModel toUsuarioFilterModel(FiltroUsuarioDTO filtroUsuarioDTO);

    @Mapping(source = "usuario.saldoTotal.valor", target = "saldoTotal")
    UsuarioResumidoDTO toUsuarioResumidoDto(Usuario usuario);

    default Page<UsuarioResumidoDTO> toUsuarioResumidoDtos(Page<Usuario> usuarios) {
        return usuarios.map(this::toUsuarioResumidoDto);
    }
}
