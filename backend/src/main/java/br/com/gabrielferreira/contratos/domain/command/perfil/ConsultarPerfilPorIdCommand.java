package br.com.gabrielferreira.contratos.domain.command.perfil;

import br.com.gabrielferreira.contratos.domain.model.Perfil;

public interface ConsultarPerfilPorIdCommand {

    Perfil execute(Long id);
}
