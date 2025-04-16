package br.com.gabrielferreira.contratos.domain.command.impl.telefone;

import br.com.gabrielferreira.contratos.domain.command.telefone.ConsultarTelefonePorIdCommand;
import br.com.gabrielferreira.contratos.domain.exception.NaoEncontradoException;
import br.com.gabrielferreira.contratos.domain.model.Telefone;
import br.com.gabrielferreira.contratos.domain.repository.TelefoneRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ConsultarTelefonePorIdCommandImpl implements ConsultarTelefonePorIdCommand {

    private final TelefoneRepository telefoneRepository;

    @Override
    public Telefone execute(Long idUsuario, Long idTelefone) {
        return telefoneRepository.buscarTelefone(idUsuario, idTelefone)
                .orElseThrow(() -> new NaoEncontradoException("Telefone não encontrado"));
    }
}
