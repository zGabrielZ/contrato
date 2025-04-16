package br.com.gabrielferreira.contratos.domain.command.impl.usuario;

import br.com.gabrielferreira.contratos.domain.command.usuario.CadastrarUsuarioCommand;
import br.com.gabrielferreira.contratos.domain.command.perfil.ConsultarPerfilPorIdCommand;
import br.com.gabrielferreira.contratos.domain.exception.RegraDeNegocioException;
import br.com.gabrielferreira.contratos.domain.mapper.CadastrarSaldoTotalUsuarioMapper;
import br.com.gabrielferreira.contratos.domain.model.Perfil;
import br.com.gabrielferreira.contratos.domain.model.SaldoTotalUsuario;
import br.com.gabrielferreira.contratos.domain.model.Usuario;
import br.com.gabrielferreira.contratos.domain.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CadastrarUsuarioCommandImpl implements CadastrarUsuarioCommand {

    private final UsuarioRepository usuarioRepository;

    private final ConsultarPerfilPorIdCommand consultarPerfilPorIdCommand;

    private final CadastrarSaldoTotalUsuarioMapper cadastrarSaldoTotalUsuarioMapper;

    @Transactional
    @Override
    public Usuario execute(Usuario usuario) {
        validarCampos(usuario);
        validarEmail(usuario.getEmail());
        validarPerfisDuplicados(usuario.getPerfis());
        validarPerfisExistentes(usuario.getPerfis());

        SaldoTotalUsuario saldoTotalUsuario = cadastrarSaldoTotalUsuarioMapper.createSaldoTotalUsuario(BigDecimal.ZERO);
        usuario.setSaldoTotal(saldoTotalUsuario);

        usuario = usuarioRepository.save(usuario);
        return usuario;
    }

    private void validarCampos(Usuario usuario) {
        usuario.setNome(usuario.getNome().trim());
        usuario.setSobrenome(usuario.getSobrenome().trim());
        usuario.setEmail(usuario.getEmail().trim());
    }

    private void validarEmail(String email) {
        boolean isEmailExistente = usuarioRepository.buscarPorEmail(email)
                .isPresent();
        if (isEmailExistente) {
            throw new RegraDeNegocioException(String.format("Este e-mail '%s' já foi cadastrado", email));
        }
    }

    private void validarPerfisDuplicados(List<Perfil> perfis) {
        List<Long> idsPerfis = perfis.stream().map(Perfil::getId).toList();
        idsPerfis.forEach(idPerfil -> {
            int duplicados = Collections.frequency(idsPerfis, idPerfil);

            if (duplicados > 1) {
                throw new RegraDeNegocioException("Não vai ser possível cadastrar este usuário pois tem perfis duplicados ou mais de duplicados");
            }
        });
    }

    private void validarPerfisExistentes(List<Perfil> perfis) {
        perfis.forEach(perfil -> {
            Perfil perfilEncontrado = consultarPerfilPorIdCommand.execute(perfil.getId());
            perfil.setDescricao(perfilEncontrado.getDescricao());
            perfil.setAutoriedade(perfilEncontrado.getAutoriedade());
        });
    }
}
