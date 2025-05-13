package br.com.gabrielferreira.contratos.application.core.model;

import br.com.gabrielferreira.contratos.application.core.enums.TipoTelefoneEnum;
import br.com.gabrielferreira.contratos.application.exception.RegraDeNegocioException;
import br.com.gabrielferreira.contratos.common.utils.TelefoneUtils;
import lombok.*;
import org.apache.commons.lang3.StringUtils;

import java.io.Serial;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = {"usuario"})
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class TelefoneModel implements Serializable {

    @Serial
    private static final long serialVersionUID = -787184941895708088L;

    @EqualsAndHashCode.Include
    private UUID id;

    private String ddd;

    private String numero;

    private String descricao;

    private TipoTelefoneEnum tipoTelefone;

    private UsuarioModel usuario;

    private ZonedDateTime dataCadastro;

    private ZonedDateTime dataAtualizacao;

    public void validarCampos() {
        this.ddd = this.ddd.trim();
        this.numero = this.numero.trim();
        if (StringUtils.isNotBlank(this.descricao)) {
            this.descricao = this.descricao.trim();
        }
    }

    public void validarTipoTelefone() {
        if (Objects.isNull(this.tipoTelefone)) {
            throw new RegraDeNegocioException("Tipo de telefone não pode ser nulo");
        } else if (TipoTelefoneEnum.isResidencial(this.tipoTelefone) && this.numero.length() == 9) {
            throw new RegraDeNegocioException(String.format("Este número informado %s está com o tipo de telefone residencial", this.getTelefoneFormatado()));
        } else if (TipoTelefoneEnum.isCelular(this.tipoTelefone) && this.numero.length() == 8) {
            throw new RegraDeNegocioException(String.format("Este número informado %s está com o tipo de telefone celular", this.getTelefoneFormatado()));
        }
    }

    public  String getTelefoneFormatado() {
        return TelefoneUtils.toMascaraTelefone(this.ddd, this.numero);
    }
}
