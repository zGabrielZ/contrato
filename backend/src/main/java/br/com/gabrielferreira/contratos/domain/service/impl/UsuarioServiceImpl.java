package br.com.gabrielferreira.contratos.domain.service.impl;

import br.com.gabrielferreira.contratos.domain.command.usuario.*;
import br.com.gabrielferreira.contratos.domain.model.Usuario;
import br.com.gabrielferreira.contratos.domain.dao.filter.UsuarioFilterModel;
import br.com.gabrielferreira.contratos.domain.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    private final CadastrarUsuarioCommand cadastrarUsuarioCommand;

    private final ConsultarUsuarioPorIdCommand consultarUsuarioPorIdCommand;

    private final ConsultarUsuarioExistentePorIdCommand consultarUsuarioExistentePorIdCommand;

    private final ConsultarUsuariosCommand consultarUsuariosCommand;

    private final AtualizarUsuarioCommand atualizarUsuarioCommand;

    private final DeletarUsuarioPorIdCommand deletarUsuarioPorIdCommand;

    @Override
    public Usuario cadastrar(Usuario usuario) {
        return cadastrarUsuarioCommand.execute(usuario);
    }

    @Override
    public Usuario consultarPorId(Long id) {
        return consultarUsuarioPorIdCommand.execute(id);
    }

    @Override
    public boolean verificarUsuarioPorId(Long id) {
        return consultarUsuarioExistentePorIdCommand.execute(id);
    }

    @Override
    public Page<Usuario> consultar(Pageable pageable, UsuarioFilterModel filtro) {
        return consultarUsuariosCommand.execute(pageable, filtro);
    }

    @Override
    public Usuario atualizar(Long id, Usuario usuario) {
        return atualizarUsuarioCommand.execute(id, usuario);
    }

    @Override
    public void deletarPorId(Long id) {
        deletarUsuarioPorIdCommand.execute(id);
    }
}
