package br.com.gabrielferreira.contratos.adapters.dto.perfil;

import lombok.Builder;

import java.io.Serializable;

@Builder
public record PerfilDTO(
        String id,
        String descricao,
        String autoriedade
) implements Serializable {
}
