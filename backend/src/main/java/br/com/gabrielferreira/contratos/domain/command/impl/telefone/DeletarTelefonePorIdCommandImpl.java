package br.com.gabrielferreira.contratos.domain.command.impl.telefone;

import br.com.gabrielferreira.contratos.domain.command.telefone.ConsultarTelefonePorIdCommand;
import br.com.gabrielferreira.contratos.domain.command.telefone.DeletarTelefonePorIdCommand;
import br.com.gabrielferreira.contratos.domain.model.Telefone;
import br.com.gabrielferreira.contratos.domain.repository.TelefoneRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class DeletarTelefonePorIdCommandImpl implements DeletarTelefonePorIdCommand {

    private final ConsultarTelefonePorIdCommand consultarTelefonePorIdCommand;

    private final TelefoneRepository telefoneRepository;

    @Transactional
    @Override
    public void execute(Long idUsuario, Long id) {
        Telefone telefoneEncontrado = consultarTelefonePorIdCommand.execute(idUsuario, id);
        telefoneRepository.delete(telefoneEncontrado);
    }
}
