package br.com.gabrielferreira.contratos.application.core.model;

import br.com.gabrielferreira.contratos.application.core.enums.SituacaoContratoEnum;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(exclude = {"parcelas", "usuario"})
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ContratoModel implements Serializable {

    @Serial
    private static final long serialVersionUID = -2945336888378069956L;

    @EqualsAndHashCode.Include
    private UUID id;

    private String numero;

    private ZonedDateTime data;

    private BigDecimal valor = BigDecimal.ZERO;

    private Integer quantidadeParcelas;

    private SituacaoContratoEnum situacaoContrato;

    private List<ParcelaModel> parcelas = new ArrayList<>();

    private UsuarioModel usuario;

    private ZonedDateTime dataCadastro;

    private ZonedDateTime dataAtualizacao;
}
