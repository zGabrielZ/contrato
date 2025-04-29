package br.com.gabrielferreira.contratos.application.core.model;

import br.com.gabrielferreira.contratos.application.core.enums.SituacaoParcelaEnum;
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
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(exclude = {"contrato"})
public class ParcelaModel implements Serializable {

    @Serial
    private static final long serialVersionUID = -6591466977470456671L;

    @EqualsAndHashCode.Include
    private UUID id;

    private ZonedDateTime data;

    private BigDecimal valor;

    private SituacaoParcelaEnum situacaoParcela;

    private ContratoModel contrato;

    private ZonedDateTime dataCadastro;

    private ZonedDateTime dataAtualizacao;

}
