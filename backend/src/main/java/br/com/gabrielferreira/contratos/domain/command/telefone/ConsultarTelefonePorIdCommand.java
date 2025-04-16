package br.com.gabrielferreira.contratos.domain.command.telefone;

import br.com.gabrielferreira.contratos.domain.model.Telefone;

public interface ConsultarTelefonePorIdCommand {

    Telefone execute(Long idUsuario, Long idTelefone);
}
