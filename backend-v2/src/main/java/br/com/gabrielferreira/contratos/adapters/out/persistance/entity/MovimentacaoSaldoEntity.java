package br.com.gabrielferreira.contratos.adapters.out.persistance.entity;

import br.com.gabrielferreira.contratos.application.core.enums.TipoMovimentacaoEnum;
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
@ToString(exclude = {"usuario"})
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "TB_MOV_SALDO")
public class MovimentacaoSaldoEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 7318115468048257082L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @EqualsAndHashCode.Include
    private UUID id;

    @JoinColumn(name = "ID_USUARIO", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private UsuarioEntity usuario;

    @Column(name = "VALOR", nullable = false, precision = 10, scale = 2)
    private BigDecimal valor;

    @Enumerated(EnumType.STRING)
    private TipoMovimentacaoEnum tipoMovimentacao;

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
