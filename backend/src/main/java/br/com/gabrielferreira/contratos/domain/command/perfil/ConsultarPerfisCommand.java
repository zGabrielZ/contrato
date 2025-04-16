package br.com.gabrielferreira.contratos.domain.command.perfil;

import br.com.gabrielferreira.contratos.domain.model.Perfil;

import java.util.List;

public interface ConsultarPerfisCommand {

    List<Perfil> execute();
}
