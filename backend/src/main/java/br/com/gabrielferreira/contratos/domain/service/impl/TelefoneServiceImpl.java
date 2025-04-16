package br.com.gabrielferreira.contratos.domain.service.impl;

import br.com.gabrielferreira.contratos.domain.command.telefone.*;
import br.com.gabrielferreira.contratos.domain.dao.filter.TelefoneFilterModel;
import br.com.gabrielferreira.contratos.domain.model.Telefone;
import br.com.gabrielferreira.contratos.domain.service.TelefoneService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TelefoneServiceImpl implements TelefoneService {

    private final CadastrarTelefoneCommand cadastrarTelefoneCommand;

    private final ConsultarTelefonePorIdCommand consultarTelefonePorIdCommand;

    private final ConsultarTelefonesCommand consultarTelefonesCommand;

    private final ConsultarQuantidadeTelefonePorUsuarioCommand consultarQuantidadeTelefonePorUsuarioCommand;

    private final AtualizarTelefoneCommand atualizarTelefoneCommand;

    private final DeletarTelefonePorIdCommand deletarTelefonePorIdCommand;

    @Override
    public Telefone cadastrar(Long idUsuario, Telefone telefone) {
        return cadastrarTelefoneCommand.execute(idUsuario, telefone);
    }

    @Override
    public Telefone consultarTelefonePorId(Long idUsuario, Long id) {
        return consultarTelefonePorIdCommand.execute(idUsuario, id);
    }

    @Override
    public Page<Telefone> consultar(Long idUsuario, Pageable pageable, TelefoneFilterModel filtro) {
        return consultarTelefonesCommand.execute(idUsuario, pageable, filtro);
    }

    @Override
    public Long buscarQuantidadePorUsuario(Long idUsuario) {
        return consultarQuantidadeTelefonePorUsuarioCommand.execute(idUsuario);
    }

    @Override
    public Telefone atualizar(Long idUsuario, Long id, Telefone telefone) {
        return atualizarTelefoneCommand.execute(idUsuario, id, telefone);
    }

    @Override
    public void deletarPorId(Long idUsuario, Long id) {
        deletarTelefonePorIdCommand.execute(idUsuario, id);
    }
}
