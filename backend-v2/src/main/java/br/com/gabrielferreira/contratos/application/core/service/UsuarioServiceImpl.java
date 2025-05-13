package br.com.gabrielferreira.contratos.application.core.service;

import br.com.gabrielferreira.contratos.application.core.model.PerfilModel;
import br.com.gabrielferreira.contratos.application.core.model.SaldoTotalModel;
import br.com.gabrielferreira.contratos.application.core.model.TelefoneModel;
import br.com.gabrielferreira.contratos.application.core.model.UsuarioModel;
import br.com.gabrielferreira.contratos.application.core.model.filtro.FiltroUsuarioModel;
import br.com.gabrielferreira.contratos.application.core.model.filtro.PageInfo;
import br.com.gabrielferreira.contratos.application.exception.NaoEncontradoException;
import br.com.gabrielferreira.contratos.application.exception.RegraDeNegocioException;
import br.com.gabrielferreira.contratos.application.ports.in.PerfilServiceInput;
import br.com.gabrielferreira.contratos.application.ports.in.UsuarioServiceInput;
import br.com.gabrielferreira.contratos.application.ports.out.PasswordEncoderOutput;
import br.com.gabrielferreira.contratos.application.ports.out.UsuarioServiceOutput;
import lombok.RequiredArgsConstructor;
import org.springframework.util.CollectionUtils;

import java.util.*;

@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioServiceInput {

    private final UsuarioServiceOutput usuarioServiceOutput;

    private final PerfilServiceInput perfilServiceInput;

    private final PasswordEncoderOutput passwordEncoderOutput;

    @Override
    public UsuarioModel cadastrar(UsuarioModel usuarioModel) {
        List<TelefoneModel> telefones = usuarioModel.getTelefones();
        usuarioModel.validarCampos();
        usuarioModel.validarSenha();

        validarEmail(usuarioModel.getEmail());
        validarPerfis(usuarioModel.getPerfis());

        usuarioModel.setSaldoTotal(new SaldoTotalModel());
        usuarioModel.setSenha(passwordEncoderOutput.encode(usuarioModel.getSenha()));
        usuarioModel.setTelefones(new ArrayList<>());
        usuarioModel = usuarioServiceOutput.salvar(usuarioModel);

        validarTelefones(telefones, usuarioModel);
        return usuarioServiceOutput.salvar(usuarioModel);
    }

    @Override
    public UsuarioModel buscarPorId(UUID id) {
        return usuarioServiceOutput.buscarPorId(id)
                .orElseThrow(() -> new NaoEncontradoException("Usuário não encontrado"));
    }

    @Override
    public UsuarioModel atualizar(UUID id, UsuarioModel usuarioModel) {
        UsuarioModel usuarioEncontrado = buscarPorId(id);
        usuarioModel.validarCampos();

        List<TelefoneModel> telefones = usuarioModel.getTelefones();
        validarTelefones(telefones, usuarioEncontrado);

        List<TelefoneModel> telefonesEncontrados = usuarioEncontrado.getTelefones();
        incluirOuAtualizarTelefones(telefones, telefonesEncontrados);
        removerTelefones(telefones, telefonesEncontrados);

        usuarioEncontrado.setTelefones(telefonesEncontrados);
        usuarioEncontrado.setNome(usuarioModel.getNome());
        usuarioEncontrado.setSobrenome(usuarioModel.getSobrenome());
        return usuarioServiceOutput.salvar(usuarioEncontrado);
    }

    @Override
    public void deletarPorId(UUID id) {
        UsuarioModel usuarioEncontrado = buscarPorId(id);
        usuarioServiceOutput.deletarPorId(usuarioEncontrado.getId());
    }

    @Override
    public List<UsuarioModel> buscar(PageInfo pageInfo, FiltroUsuarioModel filtro) {
        filtro.validarSaldoFinal();
        filtro.validarSaldoInicial();
        filtro.validarSaldoInicialComFinal();
        return usuarioServiceOutput.buscar(pageInfo, filtro);
    }

    @Override
    public List<PerfilModel> buscarPerfisPorUsuario(UUID idUsuario) {
        buscarPorId(idUsuario);
        return usuarioServiceOutput.buscarPerfisPorUsuario(idUsuario);
    }

    private void validarEmail(String email) {
        usuarioServiceOutput.buscarPorEmail(email)
                .ifPresent(usuario -> {
                    throw new RegraDeNegocioException(String.format("Este e-mail '%s' já foi cadastrado", email));
                });
    }

    private void validarPerfis(List<PerfilModel> perfis) {
        List<UUID> idsPerfis = perfis.stream().map(PerfilModel::getId).toList();
        idsPerfis.forEach(idPerfil -> {
            int duplicados = Collections.frequency(idsPerfis, idPerfil);
            if (duplicados > 1) {
                throw new RegraDeNegocioException("Não vai ser possível cadastrar este usuário pois tem perfis duplicados ou mais de duplicados");
            }
        });

        perfis.forEach(perfil -> {
            PerfilModel perfilEncontrado = perfilServiceInput.buscarPorId(perfil.getId());
            perfil.setDescricao(perfilEncontrado.getDescricao());
            perfil.setAutoriedade(perfilEncontrado.getAutoriedade());
        });
    }

    private void validarTelefones(List<TelefoneModel> telefones, UsuarioModel usuario) {
        if (!CollectionUtils.isEmpty(telefones)) {
            telefones.forEach(telefone -> {
                telefone.validarCampos();
                telefone.validarTipoTelefone();

                telefone.setUsuario(usuario);
                usuario.getTelefones().add(telefone);
            });
        }
    }

    private void incluirOuAtualizarTelefones(List<TelefoneModel> novosTelefones, List<TelefoneModel> telefonesEncontrados) {
        if (!CollectionUtils.isEmpty(novosTelefones)) {
            novosTelefones.forEach(telefone -> {
                if (Objects.nonNull(telefone.getId())) {
                    TelefoneModel telefoneEncontrado = buscarTelefone(telefone.getId(), telefonesEncontrados)
                            .orElseThrow(() -> new NaoEncontradoException("Telefone não encontrado"));
                    telefoneEncontrado.setDdd(telefone.getDdd());
                    telefoneEncontrado.setNumero(telefone.getNumero());
                    telefoneEncontrado.setDescricao(telefone.getDescricao());
                    telefoneEncontrado.setTipoTelefone(telefone.getTipoTelefone());
                } else {
                    telefonesEncontrados.add(telefone);
                }
            });
        }
    }

    private void removerTelefones(List<TelefoneModel> novosTelefones, List<TelefoneModel> telefonesEncontrados) {
        if (!CollectionUtils.isEmpty(telefonesEncontrados)) {
            telefonesEncontrados.forEach(telefone -> buscarTelefone(telefone.getId(), novosTelefones)
                    .ifPresentOrElse(
                            telefoneEncontrado -> {},
                            () -> telefonesEncontrados.remove(telefone)
                    ));
        }
    }

    private Optional<TelefoneModel> buscarTelefone(UUID telefoneId, List<TelefoneModel> telefones) {
        if (CollectionUtils.isEmpty(telefones)) {
            return Optional.empty();
        }
        return telefones.stream()
                .filter(telefoneEncontradoUsuario -> telefoneEncontradoUsuario.getId().equals(telefoneId))
                .findFirst();
    }

}
