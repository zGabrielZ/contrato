package br.com.gabrielferreira.contratos.domain.command.telefone;

public interface ConsultarQuantidadeTelefonePorUsuarioCommand {

    Long execute(Long idUsuario);
}
