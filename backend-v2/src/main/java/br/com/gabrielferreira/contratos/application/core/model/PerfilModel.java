package br.com.gabrielferreira.contratos.application.core.model;

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
public class PerfilModel implements Serializable {

    @Serial
    private static final long serialVersionUID = 1366945628371369796L;

    @EqualsAndHashCode.Include
    private UUID id;

    private String descricao;

    private String autoriedade;

    private List<UsuarioModel> usuarios = new ArrayList<>();

    public boolean isContemPerfil(List<PerfilModel> perfis) {
        return perfis.stream().anyMatch(p -> p.getId().equals(this.id));
    }

    public boolean isNaoContemPerfil(List<PerfilModel> perfis) {
        return !isContemPerfil(perfis);
    }
}
