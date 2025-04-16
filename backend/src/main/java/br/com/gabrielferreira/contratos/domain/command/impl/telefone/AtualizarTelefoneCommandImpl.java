package br.com.gabrielferreira.contratos.domain.command.impl.telefone;

import br.com.gabrielferreira.contratos.domain.command.telefone.AtualizarTelefoneCommand;
import br.com.gabrielferreira.contratos.domain.command.telefone.ConsultarTelefoneExistentePorUsuarioCommand;
import br.com.gabrielferreira.contratos.domain.command.telefone.ConsultarTelefonePorIdCommand;
import br.com.gabrielferreira.contratos.domain.exception.RegraDeNegocioException;
import br.com.gabrielferreira.contratos.domain.model.Telefone;
import br.com.gabrielferreira.contratos.domain.repository.TelefoneRepository;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class AtualizarTelefoneCommandImpl implements AtualizarTelefoneCommand {

    private final ConsultarTelefonePorIdCommand consultarTelefonePorIdCommand;

    private final ConsultarTelefoneExistentePorUsuarioCommand consultarTelefoneExistentePorUsuarioCommand;

    private final TelefoneRepository telefoneRepository;

    @Transactional
    @Override
    public Telefone execute(Long idUsuario, Long id, Telefone telefone) {
        Telefone telefoneEncontrado = consultarTelefonePorIdCommand.execute(idUsuario, id);

        validarCampos(telefone);
        validarTipoTelefone(telefone);
        validarTelefoneExistente(telefoneEncontrado.getId(), telefone);

        preencherCamposTelefone(telefoneEncontrado, telefone);

        telefoneEncontrado = telefoneRepository.saveAndFlush(telefoneEncontrado);
        return telefoneEncontrado;
    }

    private void validarCampos(Telefone telefone) {
        telefone.setDdd(telefone.getDdd().trim());
        telefone.setNumero(telefone.getNumero().trim());
        if (StringUtils.isNotBlank(telefone.getDescricao())) {
            telefone.setDescricao(telefone.getDescricao().trim());
        }
    }

    public void validarTipoTelefone(Telefone telefone) {
        if (telefone.isResidencial() && telefone.getNumero().length() == 9) {
            throw new RegraDeNegocioException(String.format("Não vai ser possível atualizar pois este número informado %s está com o tipo de telefone residencial", telefone.getNumeroFormatado()));
        } else if (telefone.isCelular() && telefone.getNumero().length() == 8) {
            throw new RegraDeNegocioException(String.format("Não vai ser possível atualizar pois este número informado %s está com o tipo de telefone celular", telefone.getNumeroFormatado()));
        }
    }

    public void validarTelefoneExistente(Long idTelefone, Telefone telefone) {
        if (consultarTelefoneExistentePorUsuarioCommand.execute(idTelefone, telefone)) {
            throw new RegraDeNegocioException(String.format("Não vai ser possível atualizar pois este telefone '%s' já foi cadastrado", telefone.getNumeroFormatado()));
        }
    }

    private void preencherCamposTelefone(Telefone telefoneEncontrado, Telefone telefone) {
        telefoneEncontrado.setDdd(telefone.getDdd());
        telefoneEncontrado.setNumero(telefone.getNumero());
        telefoneEncontrado.setDescricao(telefone.getDescricao());
        telefoneEncontrado.setTipoTelefone(telefone.getTipoTelefone());
    }
}
