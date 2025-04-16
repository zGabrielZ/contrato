package br.com.gabrielferreira.contratos.domain.command.usuario;

import br.com.gabrielferreira.contratos.domain.model.Usuario;
import br.com.gabrielferreira.contratos.domain.dao.filter.UsuarioFilterModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ConsultarUsuariosCommand {

    Page<Usuario> execute(Pageable pageable, UsuarioFilterModel filtro);
}
