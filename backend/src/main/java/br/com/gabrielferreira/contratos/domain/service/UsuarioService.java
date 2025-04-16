package br.com.gabrielferreira.contratos.domain.service;

import br.com.gabrielferreira.contratos.domain.model.Usuario;
import br.com.gabrielferreira.contratos.domain.dao.filter.UsuarioFilterModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UsuarioService {

    Usuario cadastrar(Usuario usuario);

    Usuario consultarPorId(Long id);

    boolean verificarUsuarioPorId(Long id);

    Page<Usuario> consultar(Pageable pageable, UsuarioFilterModel filtro);

    Usuario atualizar(Long id, Usuario usuario);

    void deletarPorId(Long id);
}
