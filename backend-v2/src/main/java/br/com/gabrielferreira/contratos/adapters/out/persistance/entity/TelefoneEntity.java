package br.com.gabrielferreira.contratos.adapters.out.persistance.entity;

import br.com.gabrielferreira.contratos.application.core.enums.TipoTelefoneEnum;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
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
@Table(name = "TB_TELEFONE")
public class TelefoneEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = -3076820581164855790L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @EqualsAndHashCode.Include
    private UUID id;

    @Column(name = "DDD", nullable = false)
    private String ddd;

    @Column(name = "NUMERO", nullable = false)
    private String numero;

    @Column(name = "DESCRICAO")
    private String descricao;

    @Enumerated(EnumType.STRING)
    private TipoTelefoneEnum tipoTelefone;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_USUARIO", nullable = false)
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
