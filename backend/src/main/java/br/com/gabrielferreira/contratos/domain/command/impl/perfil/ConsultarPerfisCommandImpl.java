package br.com.gabrielferreira.contratos.domain.command.impl.perfil;

import br.com.gabrielferreira.contratos.domain.command.perfil.ConsultarPerfisCommand;
import br.com.gabrielferreira.contratos.domain.model.Perfil;
import br.com.gabrielferreira.contratos.domain.repository.PerfilRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ConsultarPerfisCommandImpl implements ConsultarPerfisCommand {

    private final PerfilRepository perfilRepository;

    @Override
    public List<Perfil> execute() {
        return perfilRepository.buscarPerfis();
    }
}
