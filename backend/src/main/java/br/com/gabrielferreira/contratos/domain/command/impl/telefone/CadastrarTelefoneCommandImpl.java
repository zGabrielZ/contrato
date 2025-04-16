package br.com.gabrielferreira.contratos.domain.command.impl.telefone;

import br.com.gabrielferreira.contratos.domain.command.telefone.CadastrarTelefoneCommand;
import br.com.gabrielferreira.contratos.domain.command.telefone.ConsultarTelefoneExistentePorUsuarioCommand;
import br.com.gabrielferreira.contratos.domain.command.usuario.ConsultarUsuarioPorIdCommand;
import br.com.gabrielferreira.contratos.domain.exception.RegraDeNegocioException;
import br.com.gabrielferreira.contratos.domain.model.Telefone;
import br.com.gabrielferreira.contratos.domain.model.Usuario;
import br.com.gabrielferreira.contratos.domain.repository.TelefoneRepository;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class CadastrarTelefoneCommandImpl implements CadastrarTelefoneCommand {

    private final ConsultarUsuarioPorIdCommand consultarUsuarioPorIdCommand;

    private final ConsultarTelefoneExistentePorUsuarioCommand consultarTelefoneExistentePorUsuarioCommand;

    private final TelefoneRepository telefoneRepository;

    @Transactional
    @Override
    public Telefone execute(Long idUsuario, Telefone telefone) {
        Usuario usuario = consultarUsuarioPorIdCommand.execute(idUsuario);
        telefone.setUsuario(usuario);

        validarCampos(telefone);
        validarTipoTelefone(telefone);
        validarTelefoneExistente(null, telefone);

        telefone = telefoneRepository.save(telefone);
        return telefone;
    }

    private void validarCampos(Telefone telefone) {
        telefone.setDdd(telefone.getDdd().trim());
        telefone.setNumero(telefone.getNumero().trim());
        if (StringUtils.isNotBlank(telefone.getDescricao())) {
            telefone.setDescricao(telefone.getDescricao().trim());
        }
    }

    private void validarTipoTelefone(Telefone telefone) {
        if (telefone.isResidencial() && telefone.getNumero().length() == 9) {
            throw new RegraDeNegocioException(String.format("Este número informado %s está com o tipo de telefone residencial", telefone.getNumeroFormatado()));
        } else if (telefone.isCelular() && telefone.getNumero().length() == 8) {
            throw new RegraDeNegocioException(String.format("Este número informado %s está com o tipo de telefone celular", telefone.getNumeroFormatado()));
        }
    }

    public void validarTelefoneExistente(Long idTelefone, Telefone telefone) {
        if (consultarTelefoneExistentePorUsuarioCommand.execute(idTelefone, telefone)) {
            throw new RegraDeNegocioException(String.format("Este telefone '%s' já foi cadastrado", telefone.getNumeroFormatado()));
        }
    }
}
