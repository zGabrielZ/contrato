package br.com.gabrielferreira.contratos.domain.command.impl.usuario;

import br.com.gabrielferreira.contratos.domain.command.usuario.ConsultarUsuarioPorIdCommand;
import br.com.gabrielferreira.contratos.domain.exception.NaoEncontradoException;
import br.com.gabrielferreira.contratos.domain.model.Usuario;
import br.com.gabrielferreira.contratos.domain.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ConsultarUsuarioPorIdCommandImpl implements ConsultarUsuarioPorIdCommand {

    private final UsuarioRepository usuarioRepository;

    @Override
    public Usuario execute(Long id) {
        return usuarioRepository.buscarPorId(id)
                .orElseThrow(() -> new NaoEncontradoException("Usuário não encontrado"));
    }
}
