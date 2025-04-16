package br.com.gabrielferreira.contratos.domain.command.impl.usuario;

import br.com.gabrielferreira.contratos.domain.command.usuario.ConsultarUsuarioExistentePorIdCommand;
import br.com.gabrielferreira.contratos.domain.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ConsultarUsuarioExistentePorIdCommandImpl implements ConsultarUsuarioExistentePorIdCommand {

    private final UsuarioRepository usuarioRepository;

    @Override
    public boolean execute(Long id) {
        return usuarioRepository.buscarUsuarioExistente(id);
    }
}
