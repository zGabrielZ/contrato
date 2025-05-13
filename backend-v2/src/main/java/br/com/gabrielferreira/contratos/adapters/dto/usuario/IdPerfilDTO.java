package br.com.gabrielferreira.contratos.adapters.dto.usuario;

import lombok.Builder;

import java.io.Serializable;
import java.util.UUID;

@Builder
public record IdPerfilDTO(
        UUID id
) implements Serializable {
}
