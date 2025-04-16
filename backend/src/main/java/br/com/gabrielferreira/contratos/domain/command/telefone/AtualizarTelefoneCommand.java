package br.com.gabrielferreira.contratos.domain.command.telefone;

import br.com.gabrielferreira.contratos.domain.model.Telefone;

public interface AtualizarTelefoneCommand {

    Telefone execute(Long idUsuario, Long id, Telefone telefone);
}
