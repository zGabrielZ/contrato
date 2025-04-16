package br.com.gabrielferreira.contratos.domain.command.usuario;

import br.com.gabrielferreira.contratos.domain.model.Usuario;

public interface AtualizarUsuarioCommand {

    Usuario execute(Long id, Usuario usuario);
}
