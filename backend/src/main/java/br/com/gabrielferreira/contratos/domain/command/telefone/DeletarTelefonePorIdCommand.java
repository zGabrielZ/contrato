package br.com.gabrielferreira.contratos.domain.command.telefone;

public interface DeletarTelefonePorIdCommand {

    void execute(Long idUsuario, Long id);
}
