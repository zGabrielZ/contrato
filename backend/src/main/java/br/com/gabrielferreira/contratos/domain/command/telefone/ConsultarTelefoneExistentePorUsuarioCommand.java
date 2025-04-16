package br.com.gabrielferreira.contratos.domain.command.telefone;

import br.com.gabrielferreira.contratos.domain.model.Telefone;

public interface ConsultarTelefoneExistentePorUsuarioCommand {

    boolean execute(Long idTelefone, Telefone telefone);
}
