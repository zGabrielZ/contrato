package br.com.gabrielferreira.contratos.application.core.model;

import br.com.gabrielferreira.contratos.application.exception.RegraDeNegocioException;
import br.com.gabrielferreira.contratos.common.utils.CaracteresUtils;
import lombok.*;
import org.apache.commons.lang3.StringUtils;

import java.io.Serial;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = {"telefones", "perfis", "saldoTotal", "movimentacaoSaldos", "contratos"})
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class UsuarioModel implements Serializable {

    @Serial
    private static final long serialVersionUID = 4497383191334365065L;

    @EqualsAndHashCode.Include
    private UUID id;

    private String nome;

    private String sobrenome;

    private String email;

    private String senha;

    private List<TelefoneModel> telefones = new ArrayList<>();

    private List<PerfilModel> perfis = new ArrayList<>();

    private SaldoTotalModel saldoTotal;

    private List<MovimentacaoSaldoModel> movimentacaoSaldos = new ArrayList<>();

    private List<ContratoModel> contratos = new ArrayList<>();

    private ZonedDateTime dataCadastro;

    private ZonedDateTime dataAtualizacao;

    public void validarCampos() {
        this.nome = this.nome.trim();
        this.sobrenome = this.sobrenome.trim();
        if (StringUtils.isNotBlank(this.email)) {
            this.email = this.email.trim();
        }
    }

    public void validarSenha() {
        if (!CaracteresUtils.isPossuiCaracteresEspecias(this.senha)) {
            throw new RegraDeNegocioException("A senha informada tem que ter pelo menos uma caractere especial");
        }

        if (!CaracteresUtils.isPossuiCaractereMaiusculas(this.senha)) {
            throw new RegraDeNegocioException("A senha informada tem que ter pelo menos uma caractere maiúsculas");
        }

        if (!CaracteresUtils.isPossuiCaractereMinusculas(this.senha)) {
            throw new RegraDeNegocioException("A senha informada tem que ter pelo menos uma caractere minúsculas");
        }

        if (!CaracteresUtils.isPossuiCaractereDigito(this.senha)) {
            throw new RegraDeNegocioException("A senha informada tem que ter pelo menos um caractere dígito");
        }
    }
}
