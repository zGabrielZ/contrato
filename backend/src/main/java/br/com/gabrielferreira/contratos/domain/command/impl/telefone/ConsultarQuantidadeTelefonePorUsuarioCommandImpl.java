package br.com.gabrielferreira.contratos.domain.command.impl.telefone;

import br.com.gabrielferreira.contratos.domain.command.telefone.ConsultarQuantidadeTelefonePorUsuarioCommand;
import br.com.gabrielferreira.contratos.domain.command.usuario.ConsultarUsuarioExistentePorIdCommand;
import br.com.gabrielferreira.contratos.domain.exception.NaoEncontradoException;
import br.com.gabrielferreira.contratos.domain.repository.TelefoneRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ConsultarQuantidadeTelefonePorUsuarioCommandImpl implements ConsultarQuantidadeTelefonePorUsuarioCommand {

    private final ConsultarUsuarioExistentePorIdCommand consultarUsuarioExistentePorIdCommand;

    private final TelefoneRepository telefoneRepository;

    @Override
    public Long execute(Long idUsuario) {
        if (!consultarUsuarioExistentePorIdCommand.execute(idUsuario)) {
            throw new NaoEncontradoException("Usuário não encontrado");
        }
        return telefoneRepository.buscarQuantidadeTelefonePorUsuario(idUsuario);
    }
}
