package br.com.gabrielferreira.contratos.domain.command.impl.usuario;

import br.com.gabrielferreira.contratos.domain.command.usuario.AtualizarUsuarioCommand;
import br.com.gabrielferreira.contratos.domain.command.usuario.ConsultarUsuarioPorIdCommand;
import br.com.gabrielferreira.contratos.domain.model.Usuario;
import br.com.gabrielferreira.contratos.domain.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class AtualizarUsuarioCommandImpl implements AtualizarUsuarioCommand {

    private final ConsultarUsuarioPorIdCommand consultarUsuarioPorIdCommand;

    private final UsuarioRepository usuarioRepository;

    @Transactional
    @Override
    public Usuario execute(Long id, Usuario usuario) {
        Usuario usuarioEncontrado = consultarUsuarioPorIdCommand.execute(id);

        validarCampos(usuario);
        preencherCamposUsuario(usuarioEncontrado, usuario);

        usuarioEncontrado = usuarioRepository.saveAndFlush(usuarioEncontrado);
        return usuarioEncontrado;
    }

    private void validarCampos(Usuario usuario) {
        usuario.setNome(usuario.getNome().trim());
        usuario.setSobrenome(usuario.getSobrenome().trim());
    }

    private void preencherCamposUsuario(Usuario usuarioEncontrado, Usuario usuario){
        usuarioEncontrado.setNome(usuario.getNome());
        usuarioEncontrado.setSobrenome(usuario.getSobrenome());
    }
}
