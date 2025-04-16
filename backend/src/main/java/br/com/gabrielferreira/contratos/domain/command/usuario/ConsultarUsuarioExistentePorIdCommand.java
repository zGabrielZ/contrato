package br.com.gabrielferreira.contratos.domain.command.usuario;

public interface ConsultarUsuarioExistentePorIdCommand {

    boolean execute(Long id);
}
