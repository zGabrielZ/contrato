package br.com.gabrielferreira.contratos.domain.dao.projection;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioProjection implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;

    private String nome;

    private String sobrenome;

    private String email;

    private BigDecimal saldoTotal;

    private ZonedDateTime dataCadastro;

    private ZonedDateTime dataAtualizacao;
}
