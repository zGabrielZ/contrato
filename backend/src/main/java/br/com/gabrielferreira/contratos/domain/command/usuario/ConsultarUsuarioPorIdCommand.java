package br.com.gabrielferreira.contratos.domain.command.usuario;

import br.com.gabrielferreira.contratos.domain.model.Usuario;

public interface ConsultarUsuarioPorIdCommand {

    Usuario execute(Long id);
}
