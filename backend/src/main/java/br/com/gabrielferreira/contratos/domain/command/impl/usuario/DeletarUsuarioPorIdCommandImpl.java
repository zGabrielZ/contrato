package br.com.gabrielferreira.contratos.domain.command.impl.usuario;

import br.com.gabrielferreira.contratos.domain.command.usuario.ConsultarUsuarioPorIdCommand;
import br.com.gabrielferreira.contratos.domain.command.usuario.DeletarUsuarioPorIdCommand;
import br.com.gabrielferreira.contratos.domain.model.*;
import br.com.gabrielferreira.contratos.domain.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DeletarUsuarioPorIdCommandImpl implements DeletarUsuarioPorIdCommand {

    private final UsuarioRepository usuarioRepository;

    private final TelefoneRepository telefoneRepository;

    private final SaldoTotalUsuarioRepository saldoTotalUsuarioRepository;

    private final HistoricoSaldoRepository historicoSaldoRepository;

    private final SaldoRepository saldoRepository;

    private final ContratoRepository contratoRepository;

    private final ParcelaRepository parcelaRepository;

    private final ConsultarUsuarioPorIdCommand consultarUsuarioPorIdCommand;

    @Transactional
    @Override
    public void execute(Long id) {
        Usuario usuario = consultarUsuarioPorIdCommand.execute(id);
        List<Telefone> telefones = telefoneRepository.findAllByUsuarioId(usuario.getId());
        if (!CollectionUtils.isEmpty(telefones)) {
            telefoneRepository.deleteAll(telefones);
        }

        List<HistoricoSaldo> historicoSaldos = historicoSaldoRepository.findAllByUsuarioId(usuario.getId());
        if (!CollectionUtils.isEmpty(historicoSaldos)) {
            historicoSaldoRepository.deleteAll(historicoSaldos);
        }

        List<Saldo> saldos = saldoRepository.findAllByUsuarioId(usuario.getId());
        if (!CollectionUtils.isEmpty(saldos)) {
            saldoRepository.deleteAll(saldos);
        }

        List<Contrato> contratos = contratoRepository.findAllByUsuarioId(usuario.getId());
        if (!CollectionUtils.isEmpty(contratos)) {
            contratos.forEach(contrato -> {
                List<Parcela> parcelas = parcelaRepository.findAllByContratoId(contrato.getId());
                parcelaRepository.deleteAll(parcelas);
            });
            contratoRepository.deleteAll(contratos);
        }

        usuarioRepository.delete(usuario);
        saldoTotalUsuarioRepository.deleteById(usuario.getSaldoTotal().getId());
    }
}
