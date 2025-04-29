package br.com.gabrielferreira.contratos.application.core.model;

import br.com.gabrielferreira.contratos.application.core.enums.TipoMovimentacaoEnum;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = {"usuario"})
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class MovimentacaoSaldoModel implements Serializable {

    @Serial
    private static final long serialVersionUID = -3826697381184235539L;

    @EqualsAndHashCode.Include
    private UUID id;

    private UsuarioModel usuario;

    private BigDecimal valor = BigDecimal.ZERO;

    private TipoMovimentacaoEnum tipoMovimentacao;

    private ZonedDateTime dataCadastro;

    private ZonedDateTime dataAtualizacao;

}
