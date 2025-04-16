package br.com.gabrielferreira.contratos.domain.command.telefone;

import br.com.gabrielferreira.contratos.domain.model.Telefone;

public interface CadastrarTelefoneCommand {

    Telefone execute(Long idUsuario, Telefone telefone);
}
