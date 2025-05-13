package br.com.gabrielferreira.contratos.adapters.out.persistance.entity;

import br.com.gabrielferreira.contratos.application.core.enums.SituacaoContratoEnum;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static br.com.gabrielferreira.contratos.common.utils.DataUtils.UTC;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = {"parcelas", "usuario"})
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "TB_CONTRATO")
public class ContratoEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = -2515540737115700834L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @EqualsAndHashCode.Include
    @Column(columnDefinition = "char(36)")
    @JdbcTypeCode(SqlTypes.CHAR)
    private UUID id;

    @Column(name = "NUMERO", nullable = false)
    private String numero;

    @Column(name = "DATA", nullable = false)
    private ZonedDateTime data;

    @Column(name = "VALOR_TOTAL", nullable = false, precision = 10, scale = 2)
    private BigDecimal valor;

    @Column(name = "QUANTIDADE_PARCELAS", nullable = false)
    private Integer quantidadeParcelas;

    @Enumerated(EnumType.STRING)
    private SituacaoContratoEnum situacaoContrato;

    @OneToMany(mappedBy = "contrato", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ParcelaEntity> parcelas = new ArrayList<>();

    @JoinColumn(name = "ID_USUARIO", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private UsuarioEntity usuario;

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
