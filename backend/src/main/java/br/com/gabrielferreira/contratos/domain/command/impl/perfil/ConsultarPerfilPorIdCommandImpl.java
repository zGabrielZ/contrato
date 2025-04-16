package br.com.gabrielferreira.contratos.domain.command.impl.perfil;

import br.com.gabrielferreira.contratos.domain.command.perfil.ConsultarPerfilPorIdCommand;
import br.com.gabrielferreira.contratos.domain.exception.NaoEncontradoException;
import br.com.gabrielferreira.contratos.domain.model.Perfil;
import br.com.gabrielferreira.contratos.domain.repository.PerfilRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ConsultarPerfilPorIdCommandImpl implements ConsultarPerfilPorIdCommand {

    private final PerfilRepository perfilRepository;

    @Override
    public Perfil execute(Long id) {
        return perfilRepository.findById(id)
                .orElseThrow(() -> new NaoEncontradoException("Perfil não encontrado"));
    }
}
