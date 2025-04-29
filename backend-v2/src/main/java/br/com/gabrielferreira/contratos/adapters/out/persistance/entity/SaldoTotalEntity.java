package br.com.gabrielferreira.contratos.adapters.out.persistance.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.UUID;

import static br.com.gabrielferreira.contratos.common.utils.DataUtils.UTC;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "TB_SALDO_TOTAL")
public class SaldoTotalEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = -7712580565381967714L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @EqualsAndHashCode.Include
    private UUID id;

    @Column(name = "VALOR", nullable = false, precision = 10, scale = 2)
    private BigDecimal valor;

    @Column(name = "DATA_CADASTRO", nullable = false)
    private ZonedDateTime dataCadastro;

    @Column(name = "DATA_ATUALIZACAO")
    private ZonedDateTime dataAtualizacao;

    @PrePersist
    public void prePersist() {
        dataCadastro = ZonedDateTime.now(UTC);
    }

    @PreUpdate
    public void preUpdate() {
        dataAtualizacao = ZonedDateTime.now(UTC);
    }
}
