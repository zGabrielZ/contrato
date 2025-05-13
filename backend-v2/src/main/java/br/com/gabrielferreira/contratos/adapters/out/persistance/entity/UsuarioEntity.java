package br.com.gabrielferreira.contratos.adapters.out.persistance.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.io.Serial;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static br.com.gabrielferreira.contratos.common.utils.DataUtils.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = {"telefones", "perfis", "saldoTotal", "movimentacaoSaldos", "contratos"})
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "TB_USUARIO")
public class UsuarioEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = -890387315456852069L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @EqualsAndHashCode.Include
    @Column(columnDefinition = "char(36)")
    @JdbcTypeCode(SqlTypes.CHAR)
    private UUID id;

    @Column(name = "NOME", nullable = false)
    private String nome;

    @Column(name = "SOBRENOME", nullable = false)
    private String sobrenome;

    @Column(name = "EMAIL", nullable = false, unique = true)
    private String email;

    @Column(name = "SENHA", nullable = false)
    private String senha;

    @OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TelefoneEntity> telefones = new ArrayList<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "TB_USUARIO_PERFIL",
            joinColumns = @JoinColumn(name = "ID_USUARIO", referencedColumnName = "ID", table = "TB_USUARIO"),
            inverseJoinColumns = @JoinColumn(name = "ID_PERFIL", referencedColumnName = "ID", table = "TB_PERFIL"))
    private List<PerfilEntity> perfis = new ArrayList<>();

    @JoinColumn(name = "ID_SALDO_TOTAL", nullable = false)
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private SaldoTotalEntity saldoTotal;

    @OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<MovimentacaoSaldoEntity> movimentacaoSaldos = new ArrayList<>();

    @OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ContratoEntity> contratos = new ArrayList<>();

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
