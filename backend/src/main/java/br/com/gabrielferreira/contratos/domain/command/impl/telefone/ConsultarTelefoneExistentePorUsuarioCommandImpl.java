package br.com.gabrielferreira.contratos.domain.command.impl.telefone;

import br.com.gabrielferreira.contratos.domain.command.telefone.ConsultarTelefoneExistentePorUsuarioCommand;
import br.com.gabrielferreira.contratos.domain.model.Telefone;
import br.com.gabrielferreira.contratos.domain.repository.TelefoneRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ConsultarTelefoneExistentePorUsuarioCommandImpl implements ConsultarTelefoneExistentePorUsuarioCommand {

    private final TelefoneRepository telefoneRepository;

    @Override
    public boolean execute(Long idTelefone, Telefone telefone) {
        if(idTelefone == null){
            return telefoneRepository.buscarPorTelefone(telefone.getDdd(), telefone.getNumero(), telefone.getTipoTelefone())
                    .isPresent();
        } else {
            return telefoneRepository.buscarPorTelefone(telefone.getDdd(), telefone.getNumero(), telefone.getTipoTelefone())
                    .filter(id -> !id.equals(idTelefone))
                    .isPresent();
        }
    }
}
