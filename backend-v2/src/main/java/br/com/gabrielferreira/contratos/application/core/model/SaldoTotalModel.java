package br.com.gabrielferreira.contratos.application.core.model;

import br.com.gabrielferreira.contratos.application.exception.RegraDeNegocioException;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Objects;
import java.util.UUID;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SaldoTotalModel implements Serializable {

    @Serial
    private static final long serialVersionUID = 1547561481206257866L;

    @EqualsAndHashCode.Include
    private UUID id;

    private BigDecimal valor = BigDecimal.ZERO;

    private ZonedDateTime dataCadastro;

    private ZonedDateTime dataAtualizacao;

    public void depositar(BigDecimal quantidade) {
        RegraDeNegocioException.throwException(
                Objects.nonNull(quantidade) && quantidade.compareTo(BigDecimal.ZERO) < 0, "Não é possível depositar valor nenhum pois o saldo atual é negativo"
        );

        this.valor = this.valor.add(quantidade);
    }

    public void sacar(BigDecimal quantidade) {
        RegraDeNegocioException.throwException(
                Objects.nonNull(quantidade) && quantidade.compareTo(BigDecimal.ZERO) < 0, "Não é possível sacar valor nenhum pois o saldo atual é negativo"
        );

        RegraDeNegocioException.throwException(
                Objects.nonNull(quantidade) && quantidade.compareTo(this.valor) > 0, "Não é possível sacar valor nenhum pois a quantia informada é maior que o seu déposito"
        );

        this.valor = this.valor.subtract(quantidade);
    }
}
