package br.com.gabrielferreira.contratos.adapters.dto.usuario.create;

import lombok.Builder;

import java.io.Serializable;

@Builder
public record CreateTelefoneDTO(
        String ddd,
        String numero,
        String descricao,
        String tipoTelefone
) implements Serializable {
}
