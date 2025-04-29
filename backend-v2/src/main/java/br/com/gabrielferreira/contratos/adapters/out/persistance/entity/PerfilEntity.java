package br.com.gabrielferreira.contratos.adapters.out.persistance.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = {"usuarios"})
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "TB_PERFIL")
public class PerfilEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 2934331450149765725L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @EqualsAndHashCode.Include
    private UUID id;

    @Column(name = "DESCRICAO", nullable = false)
    private String descricao;

    @Column(name = "AUTORIEDADE", nullable = false, unique = true)
    private String autoriedade;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "perfis")
    private List<UsuarioEntity> usuarios = new ArrayList<>();
}
